package ru.nnsu.pacman.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MapHolder {
    public void Save(Map map, File file) throws FileNotFoundException, IOException {
        
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(map);
            outputStream.flush();
        }
    }

     public Map Open(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Map) inputStream.readObject();
        }
        
    }

}
