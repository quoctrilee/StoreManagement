package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.OrderDetailsModel;
import Model.ProductsModel;
import DAO.EditOrderDAO;
import DAO.OrderDetailsDAO;
import DAO.ProductsDAO;

public class OrderConController {
	private JPanel contentPanel;
	private JTable jTableLeft;
	private JTable jTableRight;
	private DefaultTableModel tableModelLeft;
	private DefaultTableModel tableModelRight;
	private int orderId;

	public OrderConController(JPanel contentPanel, JTable jTableLeft, JTable jTableRight, int orderId) {
		this.contentPanel = contentPanel;
		this.jTableLeft = jTableLeft;
		this.jTableRight = jTableRight;
		this.orderId = orderId;

		tableModelLeft = new DefaultTableModel(
				new Object[] { "Product ID", "Name", "Description", "Quantity", "Unit Price" }, 0);
		jTableLeft.setModel(tableModelLeft);

		tableModelRight = new DefaultTableModel(
				new Object[] { "Product ID", "Name", "Description", "Quantity", "Total Price" }, 0);
		jTableRight.setModel(tableModelRight);

		loadProducts();
	}

	private void loadProducts() {
		ProductsDAO productsDAO = new ProductsDAO();
		List<ProductsModel> products = productsDAO.selectAll();
		for (ProductsModel product : products) {
			tableModelLeft.addRow(new Object[] { product.getProductid(), product.getName(), product.getDescription(),
					product.getQuantity(), product.getPrice() });
		}

		EditOrderDAO editOrderDAO = new EditOrderDAO();
		List<OrderDetailsModel> orderDetailsList = editOrderDAO.selectByOrderId(orderId);

		tableModelRight.setRowCount(0); // Clear existing rows

		for (OrderDetailsModel detail : orderDetailsList) {
			ProductsModel product = editOrderDAO.selectById(detail.getProductid());
			if (product != null) {
				tableModelRight.addRow(new Object[] { detail.getProductid(), product.getName(),
						product.getDescription(), detail.getQuantity(), detail.getUnitprice() // Total price
				});
			}

		}
	}

	public void addProductToOrder(int productId, int quantity) {
	    boolean productExists = false;

	    int leftTableQuantity = getQuantityFromLeftTable(productId);

	    for (int i = 0; i < tableModelRight.getRowCount(); i++) {
	        if ((int) tableModelRight.getValueAt(i, 0) == productId) {
	            int existingQuantity = (int) tableModelRight.getValueAt(i, 3);
	            int totalQuantity = existingQuantity + quantity;
	            if (totalQuantity <= 0) {
	                // Show a message indicating that the quantity must be positive
	                JOptionPane.showMessageDialog(contentPanel, "Số lượng phải lớn hơn 0!");
	                return;
	            }
	            if (totalQuantity > leftTableQuantity) {
	                // Show a message indicating that the total quantity exceeds the available quantity in the left table
	                JOptionPane.showMessageDialog(contentPanel, "Số lượng thêm vào vượt quá số lượng hiện có của sản phẩm!");
	                return;
	            }
	            float unitPrice = (float) tableModelRight.getValueAt(i, 4) / existingQuantity;
	            tableModelRight.setValueAt(totalQuantity, i, 3);
	            tableModelRight.setValueAt(unitPrice * totalQuantity, i, 4);
	            productExists = true;
	            break;
	        }
	    }

	    if (!productExists) {
	        for (int i = 0; i < tableModelLeft.getRowCount(); i++) {
	            if ((int) tableModelLeft.getValueAt(i, 0) == productId) {
	                if (quantity <= 0) {
	                    // Show a message indicating that the quantity must be positive
	                    JOptionPane.showMessageDialog(contentPanel, "Số lượng phải lớn hơn 0!");
	                    return;
	                }
	                if (quantity > leftTableQuantity) {
	                    // Show a message indicating that the quantity exceeds the available quantity in the left table
	                    JOptionPane.showMessageDialog(contentPanel, "Số lượng thêm vào vượt quá số lượng hiện có của sản phẩm!");
	                    return;
	                }
	                float unitPrice = (float) tableModelLeft.getValueAt(i, 4);
	                float totalPrice = unitPrice * quantity;
	                Object[] rowData = { tableModelLeft.getValueAt(i, 0), tableModelLeft.getValueAt(i, 1),
	                        tableModelLeft.getValueAt(i, 2), quantity, totalPrice };
	                tableModelRight.addRow(rowData);
	                break;
	            }
	        }
	    }
	}


	private int getQuantityFromLeftTable(int productId) {
	    for (int i = 0; i < tableModelLeft.getRowCount(); i++) {
	        if ((int) tableModelLeft.getValueAt(i, 0) == productId) {
	            return (int) tableModelLeft.getValueAt(i, 3);
	        }
	    }
	    return 0;
	}
	
	
}