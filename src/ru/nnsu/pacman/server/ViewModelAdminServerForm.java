
package ru.nnsu.pacman.server;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ru.nnsu.pacman.common.Map;

public class ViewModelAdminServerForm {
    public final DefaultListModel userListModel;
    private DefaultTableModel gameTableModel;
    private ArrayList<Game> games;

    ViewModelAdminServerForm(DefaultListModel userListModel, DefaultTableModel gameTableModel) {
        this.userListModel = userListModel;
        this.gameTableModel = gameTableModel;
        this.games = new ArrayList<>();
    }
    
    public void AddUser(String nickName) {
        userListModel.addElement(nickName);
    }
    
    public Game AddGame(Map map){
        final Game game = new Game(map);
        games.add(game);
        gameTableModel.addRow(new Object[] {map.getName()});
        return game;
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

    void removeUser(String userNickName) {
        userListModel.removeElement(userNickName);
    }

    void removeGame(Game game) {
        for (int i = 0; i < gameTableModel.getRowCount(); i++) {
            String mapName = (String)gameTableModel.getValueAt(i, 0);
            if (game.getMap().getName().equals(mapName)) {
                gameTableModel.removeRow(i);
            }
        }
    }
    
}
