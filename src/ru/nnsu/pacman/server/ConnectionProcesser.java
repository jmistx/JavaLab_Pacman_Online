package ru.nnsu.pacman.server;

import ru.nnsu.pacman.common.PlayerMessage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import ru.nnsu.pacman.common.GameEvent;
import ru.nnsu.pacman.common.Map;
import ru.nnsu.pacman.common.ServerMessage;

class ConnectionProcesser implements Runnable {

    private final Socket socket;
    private final ViewModelAdminServerForm viewModel;

    ConnectionProcesser(Socket socket, ViewModelAdminServerForm viewModel) {
        this.socket = socket;
        this.viewModel = viewModel;
    }

    @Override
    public void run() {
        System.out.println("Sombody connected");
        Game game = null;
        try {
            ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());

            PlayerMessage message = (PlayerMessage) socketIn.readObject();
            viewModel.AddUser(message.getNickName());

            while (game == null) {
                PlayerMessage message2 = (PlayerMessage) socketIn.readObject();
                if (message2.getAction().equals("Create_Game")) {
                    game = viewModel.AddGame(message2.getMap());
                }
                if (message2.getAction().equals("Join_Game")) {
                    game = viewModel.joinAvailableGame();
                    ServerMessage answer = new ServerMessage();
                    if (game == null) {
                        answer.setMap(null);
                    } else {
                        answer.setMap(game.getMap());
                    }
                    socketOut.writeObject(answer);
                }
            }

            game.addObserver(new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    GameEvent event = (GameEvent) arg;
                    try {
                        ServerMessage answer = new ServerMessage();
                        answer.setGameEvent(event);
                        socketOut.writeObject(answer);
                    } catch (IOException ex) {
                        Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            while (true) {
                PlayerMessage message3 = (PlayerMessage) socketIn.readObject();
                if (message3.getAction().equals("Game_Event")) {
                    game.dispatchEvent(message3.getGameEvent());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
