package ru.nnsu.pacman.client;

import ru.nnsu.pacman.common.GameDescription;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ru.nnsu.pacman.common.Map;

public class FormCreateGame extends javax.swing.JPanel {

    private final ClientNavigator navigator;
    private final GameClient gameClient;
    private final ViewModelCreateGame viewModel;

    FormCreateGame(ClientNavigator navigator, GameClient gameClient, ViewModelCreateGame viewModel) {
        initComponents();
        this.navigator = navigator;
        this.gameClient = gameClient;
        this.viewModel = viewModel;

        this.MapComboBox.setModel(viewModel.getMapModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreateGameButton = new javax.swing.JButton();
        MapComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        JoinGame = new javax.swing.JButton();
        observeGameButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        UpdateGames = new javax.swing.JButton();

        CreateGameButton.setText("Создать игру");
        CreateGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGameButtonActionPerformed(evt);
            }
        });

        MapComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lost Temple", "Dish hample", "Cave riddings" }));

        jLabel1.setText("Выберите карту");

        JoinGame.setText("Подключиться к игре");
        JoinGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinGameActionPerformed(evt);
            }
        });

        observeGameButton.setText("Посмотреть чужую игру");
        observeGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                observeGameButtonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        UpdateGames.setText("Обновить список игр");
        UpdateGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateGamesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(observeGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JoinGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MapComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CreateGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(UpdateGames, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(MapComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateGameButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UpdateGames)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(JoinGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(observeGameButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void OnConnectionBroken() {
        JOptionPane.showMessageDialog(null, "Потеряна связь с сервером.");
        navigator.navigateToStart();
    }

    private void CreateGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGameButtonActionPerformed
        Map selectedMap = viewModel.getSelectedMap();
        DtoStartGame dto;
        try {
            dto = gameClient.CreateGame(selectedMap);
            navigator.navigateToGame(dto);
        } catch (IOException ex) {
            OnConnectionBroken();
        }
    }//GEN-LAST:event_CreateGameButtonActionPerformed

    private void JoinGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinGameActionPerformed
        try {
            DtoStartGame startGameDto = gameClient.JoinGame();
            navigator.navigateToGame(startGameDto);
        } catch (IOException ex) {
            OnConnectionBroken();
        }
    }//GEN-LAST:event_JoinGameActionPerformed

    private void observeGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_observeGameButtonActionPerformed
        try {
            DtoStartGame startGameDto = gameClient.ObserveGame();
            navigator.navigateToGame(startGameDto);
        } catch (IOException ex) {
            OnConnectionBroken();
        }
    }//GEN-LAST:event_observeGameButtonActionPerformed

    private void UpdateGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateGamesActionPerformed
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[]{"Создатель", "Карта", "Игроки"}, 0);
        List<GameDescription> games = gameClient.GetGames();
        for (GameDescription game : games) {
            defaultTableModel.addRow(new Object[]{game.getOwner(), game, game.getPlayerCount()});
        }
        jTable1.setModel(defaultTableModel);
    }//GEN-LAST:event_UpdateGamesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateGameButton;
    private javax.swing.JButton JoinGame;
    private javax.swing.JComboBox MapComboBox;
    private javax.swing.JButton UpdateGames;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton observeGameButton;
    // End of variables declaration//GEN-END:variables
}
