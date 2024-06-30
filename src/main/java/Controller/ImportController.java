package Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.ImportsDAO;
import DAO.ImportDetailsDAO;
import DAO.SuppliersDAO;
import Model.ImportsModel;
import Model.OrdersModel;
import View.EditImportView;
import View.ImportView;

public class ImportController extends BaseController {
	private ImportsDAO importDAO;
	private Set<Integer> existingImportIDs;
	private Random random;

	public ImportController(JPanel jPanel, JTable jTable) {
		super(jPanel, jTable);
		this.importDAO = new ImportsDAO();
		this.existingImportIDs = new HashSet<>(importDAO.getAllImportIDs());
		this.random = new Random();
		loadData();
	}

	public ImportController() {
		this.importDAO = new ImportsDAO();
		this.existingImportIDs = new HashSet<>(importDAO.getAllImportIDs());
		this.random = new Random();
	}

	 @Override
	    public void loadData() {
	        if (jTable != null) {
	            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
	            tableModel.setColumnIdentifiers(
	                    new Object[] {  "Mã đơn nhập", "Mã nhà sản xuất", "Ngày nhập hàng", "Trạng thái", "Tổng tiền" });
	            tableModel.setRowCount(0); // Clear existing data

	            ArrayList<ImportsModel> imports = importDAO.selectAll();
	            for (ImportsModel importItem : imports) {
	                tableModel.addRow(new Object[] { importItem.getImportid(), importItem.getSuppliersid(),
	                        importItem.getImportdate(), importItem.getStatus(), importItem.getTotal_price() });
	            }
	        } else {
	            throw new IllegalArgumentException("JTable is not initialized.");
	        }
	    }

	@Override
	public void create() {
		ImportView importView = new ImportView(this);
		importView.setVisible(true);
		importView.setLocationRelativeTo(jPanel);
	}

	@Override
	public void edit(int selectedRow) {
		if (selectedRow < 0 || jTable == null) {
			JOptionPane.showMessageDialog(jPanel, "Vui lòng chọn một đơn nhập hàng để chỉnh sửa.");
			return;
		}
		int importId = (int) jTable.getValueAt(selectedRow, 0); // Get the Import ID from the selected row
		EditImportView editImportView = new EditImportView(importId); // Pass the Import ID to the EditImportView
		editImportView.setVisible(true);
		editImportView.setLocationRelativeTo(jPanel);
		loadData();
	}

	@Override
	public void delete(int selectedRow) {
		if (selectedRow < 0 || jTable == null) {
			JOptionPane.showMessageDialog(jPanel, "Vui lòng chọn một đơn nhập hàng để xóa.");
			return;
		}
		int importId = (int) jTable.getValueAt(selectedRow, 0); // Get the Import ID from the selected row
		ImportDetailsDAO importDetailsDAO = new ImportDetailsDAO();
		importDetailsDAO.delete(importId);
		ImportsDAO importDAO = new ImportsDAO();
		importDAO.delete(importId);
		loadData();
	}

	public boolean checkSupplierExist(String name, String phone) {
		SuppliersDAO suppliersDAO = new SuppliersDAO();
		return suppliersDAO.isNamePhoneExist(name, phone);
	}

	public int generateUniqueImportID() {
		int newID;
		do {
			newID = random.nextInt(10000);
		} while (existingImportIDs.contains(newID));
		existingImportIDs.add(newID);
		return newID;
	}

	// In ImportController.java

	@Override
	public void view(int importId) {
	    // Generate the PDF file path based on the importId
	    String pdfFilePath = "target/pdf/" + importId + ".pdf";
	    viewPDF(pdfFilePath);
	}

	private void viewPDF(String filePath) {
	    try {
	        File file = new File(filePath);
	        if (file.exists()) {
	            Desktop.getDesktop().open(file);
	        } else {
	            JOptionPane.showMessageDialog(jPanel, "PDF not exist");
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void search(String searchText) {
	    if (jTable != null) {
	        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
	        tableModel.setRowCount(0); // Clear existing data

	        ArrayList<ImportsModel> orders = importDAO.selectAll();
	        for (ImportsModel order : orders) {
	            if (matchesSearch(order, searchText)) {
	                tableModel.addRow(new Object[] { order.getImportid(), order.getSuppliersid(), order.getImportdate(),
	                        order.getStatus(), order.getTotal_price() });
	            }
	        }
	    } else {
	        throw new IllegalArgumentException("JTable is not initialized.");
	    }
	}

	private boolean matchesSearch(ImportsModel order, String searchText) {
	   
	    String customerIdStr = String.valueOf(order.getSuppliersid());
	    String orderDateStr = order.getImportdate().toString();
	    String statusStr = order.getStatus();
	    String totalAmountStr = String.valueOf(order.getTotal_price());

	    return  customerIdStr.contains(searchText) ||
	           orderDateStr.contains(searchText) || statusStr.contains(searchText) ;
	          
	}
	
	

}
