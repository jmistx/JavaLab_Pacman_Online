package ru.nnsu.pacman.mapeditor;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MapEditor extends JFrame{
    
        public MapEditor() {

        initUI();
    }

    private void initUI() {

        add(new MapEditorForm());

        setSize(600, 450);

        setTitle("Pacman Map Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    } 
        public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MapEditor ex = new MapEditor();
                ex.setVisible(true);
            }
        });
    }
}
