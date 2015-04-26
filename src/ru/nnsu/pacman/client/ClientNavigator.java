package ru.nnsu.pacman.client;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class ClientNavigator {
    private static final String startWindowCard = "startWindow";
    private static final String playersWindowCard = "playersWindow";
    private static final String gameWindowCard = "gameWindow";
    
    private final CardLayout cards;
    private final FormStartClient clientStartForm;
    private final FormCreateGame createGameForm;
    private final FormGame gameForm;
    private final JPanel cardHolder;

    public ClientNavigator(CardLayout cards, JPanel cardHolder, GameClient gameClient) {
        this.cards = cards;
        this.cardHolder = cardHolder;
        clientStartForm = new FormStartClient(this, gameClient);
        createGameForm = new FormCreateGame(this, gameClient, new ViewModelCreateGame());
        gameForm = new FormGame(this);
        cardHolder.add(clientStartForm, startWindowCard);
        cardHolder.add(createGameForm, playersWindowCard);
        cardHolder.add(gameForm, gameWindowCard);
    }

    public void navigateToStart() {
        cards.show(cardHolder, startWindowCard);
    }

    public void navigateToMainMenu() {
        cards.show(cardHolder, playersWindowCard);
    }
    
    public void navigateToGame(DtoStartGame dto) {
        cards.show(cardHolder, gameWindowCard);
        gameForm.navigate(dto);
    }
}
