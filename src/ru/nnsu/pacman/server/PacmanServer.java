package ru.nnsu.pacman.server;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class PacmanServer  extends JFrame  {
    public PacmanServer() {
        initUI();
    }

    private void initUI() {
        add(new ServerStartForm());

        setSize(350, 200);

        setTitle("Pacman Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    } 
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PacmanServer ex = new PacmanServer();
                ex.setVisible(true);
            }
        });
    }
}
