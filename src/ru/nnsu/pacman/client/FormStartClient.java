package ru.nnsu.pacman.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ru.nnsu.pacman.client.ClientNavigator;
import ru.nnsu.pacman.common.PlayerMessage;
import ru.nnsu.pacman.server.PacmanServer;

public class FormStartClient extends javax.swing.JPanel {
     private final ClientNavigator navigator;
    private final GameClient client;

    public FormStartClient(ClientNavigator navigator, GameClient client) {
        initComponents();
        this.navigator = navigator;
        this.client = client;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        serverTextBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        portTextBox = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        nickNameTextBox = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Сервер");

        serverTextBox.setText("127.0.0.1");
        serverTextBox.setName("serverAddres"); // NOI18N

        jLabel2.setText("Порт");

        portTextBox.setText("6666");
        portTextBox.setName("Port"); // NOI18N

        btnConnect.setText("Подключиться");
        btnConnect.setName(""); // NOI18N
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        nickNameTextBox.setText("WhiteNigger");
        nickNameTextBox.setToolTipText("");

        jLabel3.setText("Ник");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(serverTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(portTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnConnect)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addComponent(nickNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(serverTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(portTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nickNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConnect)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        int serverPort = Integer.parseInt(portTextBox.getText());
        String address = serverTextBox.getText();
        String nickName = nickNameTextBox.getText();
        client.SetAdress(address);
        client.SetPort(serverPort);
         try {
             client.Authorize(nickName);
             navigator.navigateToMainMenu();
         } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "Сервер недоступен.");
             Logger.getLogger(FormStartClient.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }//GEN-LAST:event_btnConnectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nickNameTextBox;
    private javax.swing.JTextField portTextBox;
    private javax.swing.JTextField serverTextBox;
    // End of variables declaration//GEN-END:variables
}
