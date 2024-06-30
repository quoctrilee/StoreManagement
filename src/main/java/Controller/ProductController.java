package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import DAO.ProductsDAO;
import Model.ProductsModel;
import View.ProductsView;

public class ProductController extends BaseController {
	private ProductsDAO productsDAO;
	private Set<Integer> existingProductIDs;
	private Random random;

	public ProductController(JPanel jPanel, JTable jTable) {
		super(jPanel, jTable);
		this.productsDAO = new ProductsDAO();
		this.existingProductIDs = new HashSet<>(productsDAO.getAllProductIDs());
		this.random = new Random();
		loadData();
	}

	public ProductController() {
		this.productsDAO = new ProductsDAO();
		this.existingProductIDs = new HashSet<>(productsDAO.getAllProductIDs());
		this.random = new Random();
	}

	@Override
	public void loadData() {
		if (jTable != null) {
			DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
			tableModel.setColumnIdentifiers(
					new Object[] { "Mã sản phẩm", "Tên sản phẩm", "Mô tả", "Đơn giá", "Số lượng còn lại", "Mã mặt hàng" }); // Cập
																															// nhật
																															// tên
																															// cột
			tableModel.setRowCount(0); // Clear existing data

			List<ProductsModel> products = productsDAO.selectAll();
			for (ProductsModel product : products) {
				tableModel.addRow(new Object[] { product.getProductid(), product.getName(), product.getDescription(),
						product.getPrice(), product.getQuantity(), product.getCategoryid() });
			}
		} else {
			throw new IllegalArgumentException("JTable is not initialized.");
		}
	}

	@Override
	public void create() {
		ProductsView productsView = new ProductsView();
		productsView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		productsView.setVisible(true);

		productsView.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int productID = Integer.parseInt(productsView.getIDField().getText());
					String name = productsView.getNameField().getText();
					String description = productsView.getDesField().getText();
					float price = Float.parseFloat(productsView.getPriceField().getText());
					int quantity = Integer.parseInt(productsView.getQuantityField().getText());
					int categoryID = Integer.parseInt(productsView.getCategoriesidField().getText());

					ProductsModel newProduct = new ProductsModel(productID, name, description, price, quantity,
							categoryID);
					productsDAO.insert(newProduct);
					loadData();
					productsView.dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(productsView, "Invalid input. Please try again.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		productsView.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productsView.dispose();
			}
		});
	}

	@Override
	public void edit(int selectedRow) {
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(null, "Please select a product to edit.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
		int productID = (int) tableModel.getValueAt(selectedRow, 0);
		ProductsModel product = productsDAO.selectById(productID);

		if (product == null) {
			JOptionPane.showMessageDialog(null, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		ProductsView productsView = new ProductsView();
		productsView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		productsView.setVisible(true);

		// Populate fields with existing product data
		productsView.getIDField().setText(String.valueOf(product.getProductid()));
		productsView.getNameField().setText(product.getName());
		productsView.getDesField().setText(product.getDescription());
		productsView.getPriceField().setText(String.valueOf(product.getPrice()));
		productsView.getQuantityField().setText(String.valueOf(product.getQuantity()));
		productsView.getCategoriesidField().setText(String.valueOf(product.getCategoryid()));

		// Disable ID field to prevent modification
		productsView.getIDField().setEditable(false);

		productsView.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = productsView.getNameField().getText();
					String description = productsView.getDesField().getText();
					float price = Float.parseFloat(productsView.getPriceField().getText());
					int quantity = Integer.parseInt(productsView.getQuantityField().getText());
					int categoryID = Integer.parseInt(productsView.getCategoriesidField().getText());

					ProductsModel updatedProduct = new ProductsModel(productID, name, description, price, quantity,
							categoryID);
					productsDAO.update(updatedProduct);
					loadData();
					productsView.dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(productsView, "Invalid input. Please try again.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		productsView.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productsView.dispose();
			}
		});
	}

	@Override
	public void delete(int selectedRow) {
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(null, "Please select a product to delete.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
		int productID = (int) tableModel.getValueAt(selectedRow, 0);
		ProductsModel product = productsDAO.selectById(productID);

		if (product == null) {
			JOptionPane.showMessageDialog(null, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?",
				"Confirm Deletion", JOptionPane.YES_NO_OPTION);
		if (confirmDelete == JOptionPane.YES_OPTION) {
			productsDAO.delete(productID);
			loadData(); // Refresh the JTable
		}
	}

	public int generateUniqueProductID() {
		int newID;
		do {
			newID = random.nextInt(10000);
		} while (existingProductIDs.contains(newID));
		existingProductIDs.add(newID);
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

			ArrayList<ProductsModel> orders = productsDAO.selectAll();
			for (ProductsModel order : orders) {
				if (matchesSearch(order, searchText)) {
					tableModel.addRow(new Object[] { order.getProductid(), order.getName(), order.getDescription(),
							order.getPrice(), order.getQuantity(), order.getCategoryid() });
				}
			}
		} else {
			throw new IllegalArgumentException("JTable is not initialized.");
		}
	}

	private boolean matchesSearch(ProductsModel productsModel, String searchText) {
		String id = String.valueOf(productsModel.getProductid());
		String name = productsModel.getName();
		String des = productsModel.getDescription();
		String price = String.valueOf(productsModel.getPrice());
		String quantity = String.valueOf(productsModel.getQuantity());
		String categoryid = String.valueOf(productsModel.getCategoryid());

		return id.contains(searchText) || name.contains(searchText)
				|| des.contains(searchText) || categoryid.contains(searchText);

	}
}
