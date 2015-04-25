package ru.nnsu.pacman.server;

import ru.nnsu.pacman.common.PlayerMessage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

class ConnectionProcesser implements Runnable {

    private final Socket socket;
    private final AdminServerFormViewModel viewModel;

    ConnectionProcesser(Socket socket, AdminServerFormViewModel viewModel) {
        this.socket = socket;
        this.viewModel = viewModel;
    }

    @Override
    public void run() {
        System.out.println("Sombody connected");
        try {
            ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
            PlayerMessage message = (PlayerMessage) socketIn.readObject();
            viewModel.AddUser(message.getNickName());
            
            ObjectInputStream socketIn1 = new ObjectInputStream(socket.getInputStream());
            PlayerMessage message2 = (PlayerMessage) socketIn1.readObject();
            if (message2.getAction() == "Create_Game") {
                viewModel.AddGame();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
