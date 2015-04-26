package ru.nnsu.pacman.client;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class ClientNavigator {
    private static final String startWindowCard = "startWindow";
    private static final String playersWindowCard = "playersWindow";
    private static final String gameWindowCard = "gameWindow";
    
    private final CardLayout cards;
    private final StartClientForm clientStartForm;
    private final CreateGameForm createGameForm;
    private final GameForm gameForm;
    private final JPanel cardHolder;

    public ClientNavigator(CardLayout cards, JPanel cardHolder, GameClient gameClient) {
        this.cards = cards;
        this.cardHolder = cardHolder;
        clientStartForm = new StartClientForm(this, gameClient);
        createGameForm = new CreateGameForm(this, gameClient, new CreateGameViewModel());
        gameForm = new GameForm();
        cardHolder.add(clientStartForm, startWindowCard);
        cardHolder.add(createGameForm, playersWindowCard);
        cardHolder.add(gameForm, gameWindowCard);
    }

    public void navigateToStart() {
        cards.show(cardHolder, startWindowCard);
    }

    public void navigateToMainMenu(ClientDto dto) {
        cards.show(cardHolder, playersWindowCard);
    }
    
    public void navigateToGame(StartGameDto dto) {
        cards.show(cardHolder, gameWindowCard);
        gameForm.navigate(dto);
    }
}
