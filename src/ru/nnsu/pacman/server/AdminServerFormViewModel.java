
package ru.nnsu.pacman.server;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AdminServerFormViewModel {
    public final DefaultListModel userListModel;
    private DefaultTableModel gameTableModel;

    AdminServerFormViewModel(DefaultListModel userListModel, DefaultTableModel gameTableModel) {
        this.userListModel = userListModel;
        this.gameTableModel = gameTableModel;
    }
    
    public void AddUser(String nickName) {
        userListModel.addElement(nickName);
    }
    
    public void AddGame(String mapName){
        gameTableModel.addRow(new Object[] {mapName});
    }
    
}
