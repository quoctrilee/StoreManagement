package Controller;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public abstract class BaseController {
    protected JPanel jPanel;
    protected JTable jTable;
    protected DefaultTableModel defaultTableModel;
    
    public BaseController() {
    	
    }

    public BaseController(JPanel jPanel, JTable jTable) {
        this.jPanel = jPanel;
        this.jTable = jTable;
    }

    public abstract void loadData();

    public abstract void create();

    public abstract void edit(int selectedRow);

    public abstract void delete(int selectedRow);
    

    public abstract void view(int selectedRow);
    
    
}