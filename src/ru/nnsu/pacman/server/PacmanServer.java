package ru.nnsu.pacman.server;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PacmanServer extends JFrame {

    private CardLayout cards;
    private JPanel cardHolder;
    private static final String startWindowCard = "startWindow";
    private static final String playersWindowCard = "playersWindow";
    private Navigator navigator;

    public class Navigator {

        private final CardLayout cards;
        private final ServerStartForm serverStartForm;
        private final StartedServerForm startedServerForm;

        public Navigator(CardLayout cards, JPanel cardHolder) {
            this.cards = cards;
            serverStartForm = new ServerStartForm(this);
            startedServerForm = new StartedServerForm();
            cardHolder.add(serverStartForm, startWindowCard);
            cardHolder.add(startedServerForm, playersWindowCard);
        }

        public void navigateToStart() {
            cards.show(cardHolder, startWindowCard);
        }

        public void navigateToPlayers(PlayersDto dto) {
            startedServerForm.Navigate(dto);
            cards.show(cardHolder, playersWindowCard);
        }
    }

    public PacmanServer() {
        initUI();
    }

    private void initUI() {
        cardHolder = new JPanel();
        cards = new CardLayout();
        cardHolder.setLayout(cards);
        navigator = new Navigator(cards, cardHolder);

        add(cardHolder);
        pack();

        setSize(450, 450);

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
