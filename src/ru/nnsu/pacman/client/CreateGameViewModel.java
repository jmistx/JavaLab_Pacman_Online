package ru.nnsu.pacman.client;

import java.io.File;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

class CreateGameViewModel {

    ComboBoxModel getMapModel() {
        ArrayList<String> filesList = new ArrayList<>();
        File folder = new File("maps");
        File[] listOfFiles = folder.listFiles();
        
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                filesList.add(listOfFile.getName());
            }
        }
        String[] files = filesList.toArray(new String[filesList.size()]);
        return new DefaultComboBoxModel(files);
    }

}
