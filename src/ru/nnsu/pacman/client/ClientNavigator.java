package ru.nnsu.pacman.client;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class ClientNavigator {
    private static final String startWindowCard = "startWindow";
    private static final String playersWindowCard = "playersWindow";
    private final CardLayout cards;
    private final StartClientForm clientStartForm;
    private final CreateGameForm createGameForm;
    private final JPanel cardHolder;

    public ClientNavigator(CardLayout cards, JPanel cardHolder) {
        this.cards = cards;
        this.cardHolder = cardHolder;
        clientStartForm = new StartClientForm(this);
        createGameForm = new CreateGameForm();
        cardHolder.add(clientStartForm, startWindowCard);
        cardHolder.add(createGameForm, playersWindowCard);
    }

    public void navigateToStart() {
        cards.show(cardHolder, startWindowCard);
    }

    public void navigateToAdmin(ClientDto dto) {
        cards.show(cardHolder, playersWindowCard);
    }

}
