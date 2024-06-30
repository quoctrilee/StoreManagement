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

import DAO.CustomersDAO;
import DAO.OrderDAO;
import DAO.OrderDetailsDAO;
import Model.OrdersModel;
import View.EditOrderView;
import View.OrderView;

public class OrderController extends BaseController {
	private OrderDAO orderDAO;
	private Set<Integer> existingOrderIDs;
	private Random random;

	public OrderController(JPanel jPanel, JTable jTable) {
		super(jPanel, jTable);
		this.orderDAO = new OrderDAO();
		this.existingOrderIDs = new HashSet<>(orderDAO.getAllOrderIDs());
		this.random = new Random();
		loadData();
	}

	public OrderController() {
		this.orderDAO = new OrderDAO();
		this.existingOrderIDs = new HashSet<>(orderDAO.getAllOrderIDs());
		this.random = new Random();
	}

	 @Override
	    public void loadData() {
	        if (jTable != null) {
	            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
	            tableModel.setColumnIdentifiers(
	                    new Object[] { "Mã đơn hàng", "Mã khách hàng", "Ngày đặt hàng", "Trạng thái", "Tổng tiền" });
	            tableModel.setRowCount(0); // Clear existing data

	            ArrayList<OrdersModel> orders = orderDAO.selectAll();
	            for (OrdersModel order : orders) {
	                tableModel.addRow(new Object[] { order.getOrderid(), order.getCustomerid(), order.getOdersdate(),
	                        order.getStatus(), order.getTotalAmount() });
	            }
	        } else {
	            throw new IllegalArgumentException("JTable is not initialized.");
	        }
	    }

	@Override
	public void create() {
		OrderView orderView = new OrderView(this);
		orderView.setVisible(true);
		orderView.setLocationRelativeTo(jPanel);
		
	}

	@Override
	public void edit(int selectedRow) {
		if (selectedRow < 0 || jTable == null) {
			JOptionPane.showMessageDialog(jPanel, "Vui lòng chọn một đơn hàng để chỉnh sửa.");
			return;
		}
		int orderId = (int) jTable.getValueAt(selectedRow, 0); // Get the Order ID from the selected row
		EditOrderView editOrderView = new EditOrderView(orderId); // Pass the Order ID to the EditOrderView
		editOrderView.setVisible(true);
		editOrderView.setLocationRelativeTo(jPanel);
		loadData();
	}

	@Override
	public void delete(int selectedRow) {
		if (selectedRow < 0 || jTable == null) {
			JOptionPane.showMessageDialog(jPanel, "Vui lòng chọn một đơn hàng để chỉnh sửa.");
			return;
		}
		int orderId = (int) jTable.getValueAt(selectedRow, 0); // Get the Order ID from the selected row
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.delete(orderId);
		
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.delete(orderId);
		loadData();
		
	}

	public boolean checkCustomerExist(String name, String phone) {
		CustomersDAO customersDAO = new CustomersDAO();
		return customersDAO.isNamePhoneExist(name, phone);
	}

	public int generateUniqueOrderID() {
		int newID;
		do {
			newID = random.nextInt(10000);
		} while (existingOrderIDs.contains(newID));
		existingOrderIDs.add(newID);
		return newID;
	}

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

	        ArrayList<OrdersModel> orders = orderDAO.selectAll();
	        for (OrdersModel order : orders) {
	            if (matchesSearch(order, searchText)) {
	                tableModel.addRow(new Object[] { order.getOrderid(), order.getCustomerid(), order.getOdersdate(),
	                        order.getStatus(), order.getTotalAmount() });
	            }
	        }
	    } else {
	        throw new IllegalArgumentException("JTable is not initialized.");
	    }
	}

	private boolean matchesSearch(OrdersModel order, String searchText) {
	   
	    String customerIdStr = String.valueOf(order.getCustomerid());
	    String orderDateStr = order.getOdersdate().toString();
	    String statusStr = order.getStatus();
	    String totalAmountStr = String.valueOf(order.getTotalAmount());

	    return  customerIdStr.contains(searchText) ||
	           orderDateStr.contains(searchText) || statusStr.contains(searchText) ;
	          
	}
}
