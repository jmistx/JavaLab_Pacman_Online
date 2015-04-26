package ru.nnsu.pacman.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import ru.nnsu.pacman.common.Map;
import ru.nnsu.pacman.common.MapCell;

public class CanvasBoard extends JPanel {
    private final Map map;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
    }

    public CanvasBoard(final Map map) {
        this.map = map;
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton()==1)
                {
                CanvasBoard that = (CanvasBoard) e.getSource();
                Integer cellWidth = that.getWidth() / map.getWidth();
                Integer cellHeight = that.getHeight() / map.getHeight();
                map.SetCellValue(e.getX()/cellWidth, e.getY()/cellHeight, MapCell.WALL); 
                that.repaint();
                }
                else
                {
                CanvasBoard that = (CanvasBoard) e.getSource();
                Integer cellWidth = that.getWidth() / map.getWidth();
                Integer cellHeight = that.getHeight() / map.getHeight();
                map.SetCellValue(e.getX()/cellWidth, e.getY()/cellHeight, MapCell.PILL); 
                that.repaint();
                }
            }
        });

    }
    void drawCircleInCell(Graphics g, Color color, int cellX, int cellY, float diam) {
        Integer cellWidth = this.getWidth() / map.getWidth();
        Integer cellHeight = this.getHeight() / map.getHeight();
        
        int circleWidth = (int) (cellWidth * diam);
        int circleHeight = (int) (cellHeight * diam);
        
        int pacmanX = cellWidth * cellX + (cellWidth - circleWidth)/2;
        int pacmanY = cellHeight * cellY + (cellHeight - circleHeight)/2;
        
        g.setColor(color);
        g.fillOval(pacmanX, pacmanY, circleWidth, circleHeight);
        g.setColor(Color.black);
        g.drawOval(pacmanX, pacmanY, circleWidth, circleHeight);
    }

    private void drawMap(Graphics g) {
        Integer cellWidth = this.getWidth() / map.getWidth();
        Integer cellHeight = this.getHeight() / map.getHeight();
       
         
        for (Integer x = 0; x < map.getWidth(); x++){
          
            for (Integer y = 0; y < map.getHeight(); y++){
                
                Integer cell = map.getCellValue(x, y);
                if( cell == MapCell.WALL){
                    g.setColor(Color.blue);
                }
                else {
                    g.setColor(Color.white);
                }
                g.fillRect(x*cellWidth, y*cellHeight, cellWidth, cellHeight);
                g.setColor(Color.black);
                g.drawLine(0, y*cellHeight, cellWidth*this.getWidth(), y*cellHeight);
                
                if (cell == MapCell.PILL) {
                    
                    g.setColor(Color.BLACK);
                    drawCircleInCell(g, Color.BLACK, x, y, (float) 0.2);
                    
                }
            }
            g.setColor(Color.black);
            g.drawLine(x*cellWidth, 0, x*cellWidth, cellHeight * this.getHeight());
        }  
    }

}
