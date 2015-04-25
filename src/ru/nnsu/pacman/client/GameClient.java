package ru.nnsu.pacman.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.nnsu.pacman.common.PlayerMessage;

public class GameClient {

    private String address;
    private int serverPort;
    private Socket socket;
    private OutputStream outputStream;

    GameClient() {
    }

    Socket GetSocket() throws IOException {
        if (socket != null) {
            return socket;
        }
        InetAddress ipAddress = InetAddress.getByName(address);
        System.out.println("Try to connect with IP address " + address + " and port " + serverPort + "?");
        socket = new Socket(ipAddress, serverPort);
        System.out.println("Connected.");
        return socket;
    }
    OutputStream GetSocketOutputStream() throws IOException {
        return GetSocket().getOutputStream();
    }

    void SendMessage(PlayerMessage message) {
        try {
            OutputStream sout = GetSocket().getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(sout);  
            out.writeObject(message);
            out.flush();
            out.reset();
            //out.close();

        } catch (java.net.ConnectException x) {
            System.out.println("Connect refused");
        } catch (IOException ex) {
            Logger.getLogger(StartClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Authorize(String nickName) {
        PlayerMessage message = new PlayerMessage();
        message.setNickName(nickName);
        SendMessage(message);
    }

    void CreateGame() {
        PlayerMessage message = new PlayerMessage();
        message.setActionCreateGame();
        SendMessage(message);
    }

    void SetAdress(String address) {
        this.address = address;
    }

    void SetPort(int serverPort) {
        this.serverPort = serverPort;
    }

}
