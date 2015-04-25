package ru.nnsu.pacman.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import ru.nnsu.pacman.common.Map;
import ru.nnsu.pacman.common.MapHolder;

class CreateGameViewModel {

    ComboBoxModel getMapModel() {
        ArrayList<String> filesList = new ArrayList<>();
        MapHolder mapHolder = new MapHolder();
        File folder = new File("maps");
        File[] listOfFiles = folder.listFiles();
        
        for (File mapFile : listOfFiles) {
            if (mapFile.isFile()) {
                try {
                    Map map = mapHolder.Open(mapFile.getAbsoluteFile());
                    filesList.add(map.getName());
                } catch (IOException ex) {
                    Logger.getLogger(CreateGameViewModel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CreateGameViewModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String[] files = filesList.toArray(new String[filesList.size()]);
        return new DefaultComboBoxModel(files);
    }
}
