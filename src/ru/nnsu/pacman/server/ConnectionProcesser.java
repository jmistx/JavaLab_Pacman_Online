package ru.nnsu.pacman.server;

import ru.nnsu.pacman.common.PlayerMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.nnsu.pacman.common.GameEvent;
import ru.nnsu.pacman.common.ServerMessage;

class ConnectionProcesser implements Runnable {

    private final Socket socket;
    private final ViewModelAdminServerForm viewModel;
    private String userNickName;
    private Game game;

    ConnectionProcesser(Socket socket, ViewModelAdminServerForm viewModel) {
        this.socket = socket;
        this.viewModel = viewModel;
    }

    @Override
    public void run() {
        System.out.println("Sombody connected");
        int playerNumber = -1;

        try {
            ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());

            PlayerMessage message = (PlayerMessage) socketIn.readObject();
            userNickName = message.getNickName();
            viewModel.AddUser(userNickName);

            while (game == null) {
                PlayerMessage message2 = (PlayerMessage) socketIn.readObject();
                if (message2.isActionCreateGame()) {
                    game = viewModel.AddGame(message2.getMap());
                    playerNumber = 0;
                }

                if (message2.isActionJoinGame()) {
                    game = viewModel.joinAvailableGame();
                    playerNumber = 1;
                    ServerMessage answer = new ServerMessage();
                    if (game == null) {
                        answer.setMap(null);
                    } else {
                        answer.setMap(game.getMap());
                    }
                    socketOut.writeObject(answer);
                }

                if (message2.isActionObserveGame()) {
                    game = viewModel.observeAvailableGame();
                    playerNumber = -1;
                    ServerMessage answer = new ServerMessage();
                    if (game == null) {
                        answer.setMap(null);
                    } else {
                        answer.setMap(game.getMap());
                    }
                    socketOut.writeObject(answer);
                }
            }

            game.addObserver(new GameEventNotifier(playerNumber, socketOut));
            while (true) {
                PlayerMessage message3 = (PlayerMessage) socketIn.readObject();
                if (message3.isGameEvent()) {
                    game.dispatchEvent(message3.getGameEvent());
                }
            }
        } catch (IOException ex) {
            viewModel.removeUser(userNickName);
            if (game != null) {
                if (playerNumber != -1) {
                    game.disconnect();
                }
            }

            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (game != null) {
            if (game.isFinished()) {
                viewModel.removeGame(game);
            }
        }
    }

    private static class GameEventNotifier implements Observer {

        private final ObjectOutputStream socketOut;
        private final int playerNumber;

        public GameEventNotifier(int playerNumber, ObjectOutputStream socketOut) {
            this.playerNumber = playerNumber;
            this.socketOut = socketOut;
        }

        @Override
        public void update(Observable o, Object arg) {
            GameEvent event = (GameEvent) arg;
            if (event.getPlayerNumber() == playerNumber) {
                return;
            }
            try {
                ServerMessage answer = new ServerMessage();
                answer.setGameEvent(event);
                socketOut.writeObject(answer);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
