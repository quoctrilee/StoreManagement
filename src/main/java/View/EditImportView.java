package View;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ImportConController;
import DAO.EditImportDAO;
import DAO.ImportConDAO;
import DAO.ImportsDAO;
import DAO.ImportDetailsDAO;
import Model.ImportDetailsModel;
import Model.ProductsModel;

public class EditImportView extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable jTableLeft;
	private JTable jTableRight;
	private JTextField textField;
	private ImportConController importConController;
	private DefaultTableModel tableModelRight;
	private EditImportDAO editImportDAO; // Khai báo biến ở đây

	public EditImportView(int importId) {
		setLocationRelativeTo(null);
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 886, 404);
		contentPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel leftPanel = new JPanel();
		Color tcolor = Color.decode("#5d7b6f");
		leftPanel.setBackground(tcolor);
		leftPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		leftPanel.setBounds(0, 0, 551, 404);
		contentPanel.add(leftPanel);
		leftPanel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(236, 367, 55, 25);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setText("1"); // Set default quantity to 1
		leftPanel.add(textField);
		textField.setColumns(10);

		JButton downButton = new JButton("-");
		downButton.setBounds(160, 368, 49, 25);
		downButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		downButton.addActionListener(e -> {
			int quantity = Integer.parseInt(textField.getText());
			textField.setText(String.valueOf(Math.max(1, quantity - 1))); // Giảm số lượng đi 1 đơn vị và cập nhật lại
																			// trường số lượng
		});
		leftPanel.add(downButton);

		JButton upButton = new JButton("+");
		upButton.setBounds(319, 368, 49, 25);
		upButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		upButton.addActionListener(e -> {
			int quantity = Integer.parseInt(textField.getText());
			textField.setText(String.valueOf(quantity + 1));
		});
		leftPanel.add(upButton);

		JButton addButton = new JButton("Thêm");
		addButton.setBounds(443, 367, 71, 27);
		addButton.addActionListener(e -> {
			int selectedRow = jTableLeft.getSelectedRow();
			if (selectedRow >= 0) {
				int productId = (int) jTableLeft.getValueAt(selectedRow, 0);
				try {
					int quantity = Integer.parseInt(textField.getText());
					importConController.addProductToImport(productId, quantity);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
		leftPanel.add(addButton);

		JLabel addProductLabel = new JLabel("Thêm sản phẩm");
		addProductLabel.setBackground(tcolor);
		addProductLabel.setOpaque(true);
		addProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addProductLabel.setBounds(0, 0, 551, 54);
		leftPanel.add(addProductLabel);
		addProductLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		JScrollPane scrollPaneLeft = new JScrollPane();
		scrollPaneLeft.setBounds(0, 52, 551, 295);
		leftPanel.add(scrollPaneLeft);

		jTableLeft = new JTable();
		scrollPaneLeft.setViewportView(jTableLeft);

		jTableLeft.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				textField.setText("1");
			}
		});

		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(true);
		Color icolor = Color.decode("#b0d4b8");
		rightPanel.setBackground(icolor);
		rightPanel.setLayout(null);
		rightPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rightPanel.setBounds(550, 0, 336, 404);
		contentPanel.add(rightPanel);

		JScrollPane scrollPaneRight = new JScrollPane();
		scrollPaneRight.setBounds(10, 51, 316, 353);
		rightPanel.add(scrollPaneRight);

		jTableRight = new JTable();
		tableModelRight = new DefaultTableModel(new Object[][] {},
				new String[] { "Product ID", "Product Name", "Description", "Quantity", "Total Price" });
		jTableRight.setModel(tableModelRight);
		scrollPaneRight.setViewportView(jTableRight);

		JLabel importIdLabel = new JLabel("Import ID: " + importId);

		importIdLabel.setBackground(icolor);
		importIdLabel.setOpaque(true);
		importIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		importIdLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		importIdLabel.setBounds(0, 0, 336, 52);
		rightPanel.add(importIdLabel);

		JPanel buttonPane = new JPanel();
		Color bcolor = Color.decode("#EAE7D6");
		buttonPane.setBackground(bcolor);

		buttonPane.setBounds(0, 403, 886, 60);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(null);

		JButton okButton = new JButton("OK");
		okButton.setBounds(693, 20, 78, 30);
		buttonPane.add(okButton);
		okButton.addActionListener(e -> {
			if (editImportDAO.deleteImportDetailsByImportId(importId)) {
				ImportDetailsDAO importDetailsDAO = new ImportDetailsDAO();
				DefaultTableModel model = (DefaultTableModel) jTableRight.getModel();
				int rowCount = model.getRowCount();
				for (int i = 0; i < rowCount; i++) {
					ImportDetailsModel importDetailsModel = new ImportDetailsModel();
					importDetailsModel.setImportid(importId);
					importDetailsModel.setImportdetailid(importId); // Tạo id duy nhất
					importDetailsModel.setProductd((int) model.getValueAt(i, 0)); // Lấy ID sản phẩm từ bảng
					importDetailsModel.setQuantity((int) model.getValueAt(i, 3)); // Lấy số lượng từ bảng
					importDetailsModel.setUnitprice((float) model.getValueAt(i, 4)); // Lấy đơn giá từ bảng

					int rowsAffected = importDetailsDAO.insert(importDetailsModel); // Thêm chi tiết nhập hàng vào cơ sở
																					// dữ liệu
					if (rowsAffected > 0) {
						ImportView importView = new ImportView();
						dispose();
					}
				}

				ImportConDAO importConDAO = new ImportConDAO();
				float total = importConDAO.total(importId);
				ImportsDAO importDAO = new ImportsDAO();
				importDAO.updateTotalAmountById(importId, total);
			}
		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(785, 20, 78, 30);
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(e -> {
			dispose();
		});

		// Initialize controller
		importConController = new ImportConController(contentPanel, jTableLeft, jTableRight, importId);

		// Initialize DAO
		editImportDAO = new EditImportDAO();

		// Load import details
		loadImportDetails(importId);
	}

	private void loadImportDetails(int importId) {
		List<ImportDetailsModel> importDetailsList = editImportDAO.selectByImportId(importId);
		tableModelRight.setRowCount(0); // Clear existing rows

		for (ImportDetailsModel detail : importDetailsList) {
			ProductsModel product = editImportDAO.selectById(detail.getProductd());
			if (product != null) {
				tableModelRight.addRow(new Object[] { detail.getProductd(), product.getName(), product.getDescription(),
						detail.getQuantity(), detail.getQuantity() * detail.getUnitprice() // Total price
				});
			}
		}
	}

	/**
	 * Create the dialog.
	 */
}
