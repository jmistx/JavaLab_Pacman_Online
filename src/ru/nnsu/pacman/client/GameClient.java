package ru.nnsu.pacman.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.nnsu.pacman.common.PlayerMessage;

/**
 *
 * @author JM
 */
public class GameClient {

    private final String address;
    private final int serverPort;
    private Socket socket;

    GameClient(String address, int serverPort) {
        this.address = address;
        this.serverPort = serverPort;
    }

    OutputStream GetSocketOutputStream() throws IOException {
        if (socket != null) {
            return socket.getOutputStream();
        }
        InetAddress ipAddress = InetAddress.getByName(address);
        System.out.println("Try to connect with IP address " + address + " and port " + serverPort + "?");
        socket = new Socket(ipAddress, serverPort);
        System.out.println("Connected.");
        return socket.getOutputStream();
    }
    
    void SendMessage(PlayerMessage message) {
        try {
            try (OutputStream sout = GetSocketOutputStream()) {
                ObjectOutputStream out = new ObjectOutputStream(sout);
                out.writeObject(message);
                out.flush();
            }
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
            
    }

}
