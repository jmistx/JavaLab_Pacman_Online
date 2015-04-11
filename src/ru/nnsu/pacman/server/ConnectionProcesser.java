package ru.nnsu.pacman.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
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
            InputStream sin = socket.getInputStream();
            DataInputStream in = new DataInputStream(sin);
            String nickName = in.readUTF();
            userListModel.addElement(nickName);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionProcesser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
