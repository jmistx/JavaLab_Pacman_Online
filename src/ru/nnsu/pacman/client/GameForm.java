package ru.nnsu.pacman.client;

import java.awt.Color;
import java.awt.Graphics;
import ru.nnsu.pacman.common.Map;

public class GameForm extends javax.swing.JPanel {
    private Map map;

    public GameForm() {
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void drawMap(Graphics g) {
        if (this.map == null) return;
        
        Integer cellWidth = this.getWidth() / map.getWidth();
        Integer cellHeight = this.getHeight() / map.getHeight();
       
         
        for (Integer x = 0; x < map.getWidth(); x++){
          
            for (Integer y = 0; y < map.getHeight(); y++){
                
                Integer value = map.getCellValue(x, y);
                if( value == 1){
                    g.setColor(Color.blue);
                }
                else {
                    g.setColor(Color.white);
                }
                g.fillRect(x*cellWidth, y*cellHeight, cellWidth, cellHeight);
                g.setColor(Color.black);
                g.drawLine(0, y*cellHeight, cellWidth*this.getWidth(), y*cellHeight);
            }
            g.setColor(Color.black);
            g.drawLine(x*cellWidth, 0, x*cellWidth, cellHeight * this.getHeight());
        } 
    }

    void navigate(StartGameDto dto) {
        this.map = dto.getMap();
    }
}
