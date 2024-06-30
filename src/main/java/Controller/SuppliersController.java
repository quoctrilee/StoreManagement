
package Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import DAO.SuppliersDAO;
import Model.SuppliersModel;
import View.SuppliersView;

public class SuppliersController extends BaseController {
    private SuppliersDAO suppliersDAO;
    private Set<Integer> existingSupplierIDs;
    private Random random;

    public SuppliersController(JPanel jPanel, JTable jTable) {
        super(jPanel, jTable);
        this.suppliersDAO = new SuppliersDAO();
        this.existingSupplierIDs =  new HashSet<> (suppliersDAO.getAllSupplierIDs());
        this.random = new Random();
        loadData();
    }

    public SuppliersController() {
        this.suppliersDAO = new SuppliersDAO();
        this.existingSupplierIDs =  new HashSet<>( suppliersDAO.getAllSupplierIDs());
        this.random = new Random();
    }

    @Override
    public void loadData() {
        if (jTable != null) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.setColumnIdentifiers(new Object[]{"Mã nhà cung cấp", "Tên", "Địa chỉ", "Số điện thoại"});
            tableModel.setRowCount(0);

            ArrayList<SuppliersModel> suppliers = suppliersDAO.selectAll();
            for (SuppliersModel supplier : suppliers) {
                tableModel.addRow(new Object[]{
                        supplier.getSuppliersid(),
                        supplier.getName(),
                        supplier.getAddress(),
                        supplier.getPhone()
                });
            }
        } else {
            throw new IllegalArgumentException("JTable is not initialized.");
        }
    }

    @Override
    public void create() {
        SuppliersView supplierView = new SuppliersView();
        supplierView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        supplierView.setVisible(true);

        supplierView.getOkButton().addActionListener(e -> {
            try {
                int supplierId = Integer.parseInt(supplierView.getIdField().getText());
                String name = supplierView.getNameField().getText();
                String address = supplierView.getAddressField().getText();
                String phone = supplierView.getPhoneField().getText();

                SuppliersModel newSupplier = new SuppliersModel(supplierId, name, address, phone);
                suppliersDAO.insert(newSupplier);
                loadData();
                supplierView.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(supplierView, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        supplierView.getCancelButton().addActionListener(e -> supplierView.dispose());
    }

    @Override
    public void edit(int selectedRow) {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        int supplierId = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String address = (String) tableModel.getValueAt(selectedRow, 2);
        String phone = (String) tableModel.getValueAt(selectedRow, 3);

        SuppliersView supplierView = new SuppliersView();
        supplierView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        supplierView.setVisible(true);

        supplierView.getIdField().setText(String.valueOf(supplierId));
        supplierView.getNameField().setText(name);
        supplierView.getAddressField().setText(address);
        supplierView.getPhoneField().setText(phone);

        supplierView.getOkButton().addActionListener(e -> {
            try {
                String newName = supplierView.getNameField().getText();
                String newAddress = supplierView.getAddressField().getText();
                String newPhone = supplierView.getPhoneField().getText();

                SuppliersModel updatedSupplier = new SuppliersModel(supplierId, newName, newAddress, newPhone);
                suppliersDAO.update(updatedSupplier);
                loadData();
                supplierView.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(supplierView, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        supplierView.getCancelButton().addActionListener(e -> supplierView.dispose());
    }

    @Override
    public void delete(int selectedRow) {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        int supplierId = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this supplier?");
        if (confirm == JOptionPane.YES_OPTION) {
            suppliersDAO.delete(supplierId);
            loadData(); // Refresh table data
        }
    }

    public int generateUniqueSupplierID() {
        int newID;
        do {
            newID = random.nextInt(10000);
        } while (existingSupplierIDs.contains(newID));
        existingSupplierIDs.add(newID);
        return newID;
    }

	@Override
	public void view(int selectedRow) {
		// TODO Auto-generated method stub
		
	}
}
