package ru.nnsu.pacman.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class AdminServerForm extends javax.swing.JPanel {

    private final DefaultListModel userListModel;

    public AdminServerForm() {
        initComponents();
        userListModel = new DefaultListModel();
        playersListBox.setModel(userListModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        playersListBox = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(playersListBox);

        jLabel1.setText("Игроки на сервере");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList playersListBox;
    // End of variables declaration//GEN-END:variables

    private void connect(final int port) {

        Thread connectionHolderThread = new Thread() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                while (true) {
                    try {
                        serverSocket = new ServerSocket(port);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerStartForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        System.out.println("Waiting for a client...");
                        Socket socket = serverSocket.accept();
                        ConnectionProcesser processer = new ConnectionProcesser(socket, userListModel);
                        Thread processerThread = new Thread(processer);
                        processerThread.start();
                    } catch (IOException ex) {
                        Logger.getLogger(ServerStartForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        connectionHolderThread.start();

    }

    void Navigate(AdminDto dto) {
        connect(dto.getPort());
    }
}
