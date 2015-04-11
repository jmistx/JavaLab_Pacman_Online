package ru.nnsu.pacman.client;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PacmanClient extends JFrame  {
    public PacmanClient() {

        initUI();
    }

    private void initUI() {

        add(new StartForm());

        setSize(350, 200);

        setTitle("PacmanGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    } 
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PacmanClient ex = new PacmanClient();
                ex.setVisible(true);
            }
        });
    }
    
}
