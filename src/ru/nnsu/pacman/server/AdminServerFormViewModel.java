
package ru.nnsu.pacman.server;

import javax.swing.DefaultListModel;
import javax.swing.table.TableModel;

public class AdminServerFormViewModel {
    public final DefaultListModel userListModel;
    private TableModel gameTableModel;

    AdminServerFormViewModel(DefaultListModel userListModel, TableModel gameTableModel) {
        this.userListModel = userListModel;
        this.gameTableModel = gameTableModel;
    }
    
    public void AddUser(String nickName) {
        userListModel.addElement(nickName);
    }
    
    public void AddGame(){
        gameTableModel.setValueAt("blah", 1, 1);
    }
    
}
