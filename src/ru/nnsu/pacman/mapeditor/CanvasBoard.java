package ru.nnsu.pacman.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ru.nnsu.pacman.common.Map;

public class CanvasBoard extends JPanel {
    private final Map map;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
    }

    private void paintSomething(Graphics g) {
        g.fillOval(10, 10, 10, 10);
        g.drawOval(10, 10, 10, 10);
    }

    public CanvasBoard(final Map map) {
        this.map = map;
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Integer But = e.getButton();
                CanvasBoard that = (CanvasBoard) e.getSource();
                
                Integer cellWidth = that.getWidth() / map.getWidth();
                Integer cellHeight = that.getHeight() / map.getHeight();
                map.SetCellAsWall(e.getX()/cellWidth, e.getY()/cellHeight);
                
                that.setBackground(Color.pink);
                that.repaint();
            }
        });

    }

    private void drawMap(Graphics g) {
        Integer cellWidth = this.getWidth() / map.getWidth();
        Integer cellHeight = this.getHeight() / map.getHeight();
        
        for (Integer x = 0; x < map.getWidth(); x++){
            for (Integer y = 0; y < map.getHeight(); y++){
                Integer value = map.GetCellValue(x, y);
                if( value == 1){
                    g.setColor(Color.green);
                }
                else {
                    g.setColor(Color.black);
                }
                g.fillRect(x*cellWidth, y*cellHeight, cellWidth, cellHeight);
            }
        }
    }

}
