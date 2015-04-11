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
    private final DefaultListModel userListModel;

    ConnectionProcesser(Socket socket, DefaultListModel userListModel) {
        this.socket = socket;
        this.userListModel = userListModel;
    }

    @Override
    public void run() {
        System.out.println("Sombody connected");
        try {
            ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
            PlayerMessage message = (PlayerMessage) socketIn.readObject();
            userListModel.addElement(message.getNickName());
        } catch (IOException ex) {
            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
