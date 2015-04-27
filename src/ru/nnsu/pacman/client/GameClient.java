package ru.nnsu.pacman.client;

import ru.nnsu.pacman.common.ServerMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.nnsu.pacman.common.GameEvent;
import ru.nnsu.pacman.common.Map;
import ru.nnsu.pacman.common.PlayerMessage;

public class GameClient {

    private String address;
    private int serverPort;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    GameClient() {
    }

    private Socket GetSocket() throws IOException {
        if (socket != null) {
            return socket;
        }
        InetAddress ipAddress = InetAddress.getByName(address);
        System.out.println("Try to connect with IP address " + address + " and port " + serverPort + "?");
        socket = new Socket(ipAddress, serverPort);
        System.out.println("Connected.");
        return socket;
    }
    private ObjectOutputStream GetObjectOutputStream() throws IOException {
        if (outputStream != null) {
            return outputStream;
        }
        OutputStream sout = GetSocket().getOutputStream();
        outputStream = new ObjectOutputStream(sout); 
        return outputStream;
    }
    
    private ObjectInputStream GetObjectInputStream() throws IOException {
        if (inputStream != null ){
            return inputStream;
        }
        
        InputStream sout = GetSocket().getInputStream();
        inputStream = new ObjectInputStream(sout); 
        return inputStream;
    }

    private void SendMessage(PlayerMessage message) throws IOException {
            ObjectOutputStream out = GetObjectOutputStream();  
            out.writeObject(message);
            out.flush();
    }
    
    private ServerMessage ReadMessage() throws IOException {
        try {
            ObjectInputStream in = GetObjectInputStream();  
            ServerMessage message = (ServerMessage)in.readObject();
            return message;
        } catch (java.net.ConnectException x) {
            System.out.println("Connect refused");
        } catch (IOException ex) {
            Logger.getLogger(FormStartClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new IOException("Server Unavailable");
    }
    
    void Authorize(String nickName) throws IOException{
        PlayerMessage message = new PlayerMessage();
        message.setNickName(nickName);
        SendMessage(message);
    }

    void SetAdress(String address) {
        this.address = address;
    }

    void SetPort(int serverPort) {
        this.serverPort = serverPort;
    }

    DtoStartGame CreateGame(Map map) throws IOException{
        PlayerMessage message = new PlayerMessage();
        message.setActionCreateGame();
        message.setMap(map);
        SendMessage(message);
        return new DtoStartGame(map, 0, this);
    }
    
    DtoStartGame JoinGame() throws IOException {
        PlayerMessage message = new PlayerMessage();
        message.setActionJoinGame();
        SendMessage(message);
        ServerMessage answer = ReadMessage();
        return new DtoStartGame(answer.getMap(), 1, this);
    }

    void SendEvent(GameEvent gameEvent) throws IOException{
        PlayerMessage message = new PlayerMessage();
        message.setGameEvent(gameEvent);
        SendMessage(message);
    }

    GameEvent GetNextEvent() throws IOException {
        ServerMessage answer = ReadMessage();
        return answer.getGameEvent();
    }

}
