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
import DAO.CustomersDAO;
import Model.CustomersModel;
import View.CustomerView;

public class CustomerController extends BaseController {
    private CustomersDAO customersDAO;
    private Set<Integer> existingCustomerIDs;
    private Random random;

    public CustomerController(JPanel jPanel, JTable jTable) {
        super(jPanel, jTable);
        this.customersDAO = new CustomersDAO();
        this.existingCustomerIDs = new HashSet<>(customersDAO.getAllCustomerIDs());
        this.random = new Random();
        loadData();
    }
    
    public CustomerController() {
        this.customersDAO = new CustomersDAO();
        this.existingCustomerIDs = new HashSet<>(customersDAO.getAllCustomerIDs());
        this.random = new Random();
    }

    @Override
    public void loadData() {
        if (jTable != null) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.setColumnIdentifiers(new Object[]{"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ"});
            tableModel.setRowCount(0); // Clear existing data

            ArrayList<CustomersModel> customers = customersDAO.selectAll();
            for (CustomersModel customer : customers) {
                tableModel.addRow(new Object[]{
                        customer.getCustomerid(),
                        customer.getName(),
                        customer.getPhone(),
                        customer.getAddress()
                });
            }
        } else {
            throw new IllegalArgumentException("JTable is not initialized.");
        }
    }

    @Override
    public void create() {
        CustomerView customerView = new CustomerView();
        customerView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        customerView.setVisible(true);

        customerView.getOkButton().addActionListener(e -> {
            try {
                int customerId = generateUniqueCustomerID();
                String name = customerView.getNameField().getText();
                String phone = customerView.getPhoneField().getText();
                String address = customerView.getAddressField().getText();

                if (customersDAO.isNamePhoneExist(name, phone)) {
                    JOptionPane.showMessageDialog(customerView, "Customer already exists!");
                } else {
                    CustomersModel newCustomer = new CustomersModel(customerId, name, address, phone);
                    customersDAO.insert(newCustomer);
                    loadData();
                    customerView.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(customerView, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        customerView.getCancelButton().addActionListener(e -> customerView.dispose());
    }

    @Override
    public void edit(int selectedRow) {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        int customerId = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String phone = (String) tableModel.getValueAt(selectedRow, 2);
        String address = (String) tableModel.getValueAt(selectedRow, 3);

        CustomerView customerView = new CustomerView();
        customerView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        customerView.setVisible(true);

        customerView.getIdField().setText(String.valueOf(customerId));
        customerView.getNameField().setText(name);
        customerView.getPhoneField().setText(phone);
        customerView.getAddressField().setText(address);

        customerView.getOkButton().addActionListener(e -> {
            try {
                String newName = customerView.getNameField().getText();
                String newPhone = customerView.getPhoneField().getText();
                String newAddress = customerView.getAddressField().getText();

                CustomersModel updatedCustomer = new CustomersModel(customerId, newName, newAddress, newPhone);
                customersDAO.update(updatedCustomer);
                loadData();
                customerView.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(customerView, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        customerView.getCancelButton().addActionListener(e -> customerView.dispose());
    }

    @Override
    public void delete(int selectedRow) {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        int customerId = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?");
        if (confirm == JOptionPane.YES_OPTION) {
            customersDAO.delete(customerId);
            loadData(); // Refresh table data
        }
    }

    public int generateUniqueCustomerID() {
        int newID;
        do {
            newID = random.nextInt(10000);
        } while (existingCustomerIDs.contains(newID));
        existingCustomerIDs.add(newID);
        return newID;
    }

	@Override
	public void view(int selectedRow) {
		// TODO Auto-generated method stub
		
	}
	
	public void search(String searchText) {
	    if (jTable != null) {
	        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
	        tableModel.setRowCount(0); // Clear existing data

	        ArrayList<CustomersModel> orders = customersDAO.selectAll();
	        for (CustomersModel order : orders) {
	            if (matchesSearch(order, searchText)) {
	                tableModel.addRow(new Object[] { order.getCustomerid(), order.getName(), order.getAddress(),
	                        order.getPhone() });
	            }
	        }
	    } else {
	        throw new IllegalArgumentException("JTable is not initialized.");
	    }
	}

	private boolean matchesSearch(CustomersModel customersModel, String searchText) {
	   
	    String customerIdStr = String.valueOf(customersModel.getCustomerid());
	    String name = customersModel.getName();
	    String address = customersModel.getAddress();
	    String phone = customersModel.getPhone();

	    return  customerIdStr.contains(searchText) ||
	           name.contains(searchText) || address.contains(searchText) || phone.contains(searchText) ;
	          
	}
    
}
