package ru.nnsu.pacman.mapeditor;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ru.nnsu.pacman.common.Map;


public class MapEditorForm extends javax.swing.JPanel {
    
    private Map map;
    private  MapSaver mapSaver;
    
    public MapEditorForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SaveButton = new javax.swing.JButton();
        OpenButton = new javax.swing.JButton();
        CreateButton = new javax.swing.JButton();
        CanvasBoardContainer = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        WidthText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        HeightText = new javax.swing.JTextField();
        MapName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        SaveButton.setText("Сохранить");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        OpenButton.setText("Открыть");
        OpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenButtonActionPerformed(evt);
            }
        });

        CreateButton.setText("Создать");
        CreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Ширина");

        jLabel2.setText("Высота");

        jLabel3.setText("Название");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(OpenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CreateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(WidthText)
                        .addComponent(HeightText)
                        .addComponent(MapName))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CanvasBoardContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CanvasBoardContainer)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(OpenButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton)
                        .addGap(43, 43, 43)
                        .addComponent(CreateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(WidthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HeightText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(MapName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SetCurrentMap(Map map){
        this.map = map;
        CanvasBoard board = new CanvasBoard(map);
        board.setBounds(0, 0, 500, 500);
        board.setPreferredSize(new Dimension(500, 500));
        board.setVisible(true);
        CanvasBoardContainer.setViewportView(board);
    }
    private void CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateButtonActionPerformed
        
        try{
        final int width = Integer.parseInt(WidthText.getText());
        final int height = Integer.parseInt(HeightText.getText());
        if (!"".equals(MapName.getText())){
        final String name = MapName.getText();
        SetCurrentMap(new Map(width, height, name));
        }
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }  
    }//GEN-LAST:event_CreateButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
         mapSaver = new MapSaver();
        
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                mapSaver.Save(map, file);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void OpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenButtonActionPerformed
        mapSaver = new MapSaver();
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                SetCurrentMap(mapSaver.Open(file));
                
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_OpenButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane CanvasBoardContainer;
    private javax.swing.JButton CreateButton;
    private javax.swing.JTextField HeightText;
    private javax.swing.JTextField MapName;
    private javax.swing.JButton OpenButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField WidthText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
