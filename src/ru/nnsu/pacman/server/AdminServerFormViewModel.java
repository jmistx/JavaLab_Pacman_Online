
package ru.nnsu.pacman.server;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ru.nnsu.pacman.common.Map;

public class AdminServerFormViewModel {
    public final DefaultListModel userListModel;
    private DefaultTableModel gameTableModel;
    private ArrayList<Game> games;

    AdminServerFormViewModel(DefaultListModel userListModel, DefaultTableModel gameTableModel) {
        this.userListModel = userListModel;
        this.gameTableModel = gameTableModel;
        this.games = new ArrayList<>();
    }
    
    public void AddUser(String nickName) {
        userListModel.addElement(nickName);
    }
    
    public void AddGame(Map map){
        games.add(new Game(map));
        gameTableModel.addRow(new Object[] {map.getName()});
    }

    Game joinAvailableGame() {
        for (Game game : games){
            if (game.hasFreeSlot()) {
                game.join();
                return game;
            }
        }
        return null;
    }
    
}
