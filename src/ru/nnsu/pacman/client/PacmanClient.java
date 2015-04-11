package ru.nnsu.pacman.client;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PacmanClient extends JFrame  {
    
   private CardLayout cards;
    private JPanel cardHolder;
    private ClientNavigator navigator;

    public PacmanClient() {
        initUI();
    }

    private void initUI() {
        cardHolder = new JPanel();
        cards = new CardLayout();
        cardHolder.setLayout(cards);
        navigator = new ClientNavigator(cards, cardHolder);

        add(cardHolder);
        pack();

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
