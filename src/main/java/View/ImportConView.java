package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import javax.swing.JOptionPane;

import Controller.ImportConController;
import DAO.ImportDetailsDAO;
import Model.ImportDetailsModel;
import PDF.PDF;

public class ImportConView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable jTableLeft;
	private JTable jTableRight;
	private JTextField textField;
	private ImportConController importConController;
	private String[] customerInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ImportConView dialog = new ImportConView(123, new String[] { "John Doe", "123456789", "123 Street" }); // Assume
																													// 123
																													// is
																													// the
																													// import
																													// ID
																													// from
																													// ImportView
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ImportConView(int importId, String[] customerInfo) {
		this.customerInfo = customerInfo;
		setLocationRelativeTo(null);
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 886, 404);
		contentPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		ImageIcon icon = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\logo.jpg");

		// Chỉnh sửa kích thước hình ảnh nếu cần thiết
		Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		// Tạo một ImageIcon mới từ hình ảnh đã được chỉnh sửa
		ImageIcon scaledIcon = new ImageIcon(image);

		// Đặt icon cho JFrame
		setIconImage(scaledIcon.getImage());
		setLocationRelativeTo(null);

		JPanel leftPanel = new JPanel();
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
			textField.setText(String.valueOf(quantity - 1));
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
		addProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addProductLabel.setBounds(0, 0, 551, 54);
		leftPanel.add(addProductLabel);
		addProductLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		JScrollPane scrollPaneLeft = new JScrollPane();
		scrollPaneLeft.setBounds(0, 52, 551, 295);
		leftPanel.add(scrollPaneLeft);

		jTableLeft = new JTable();
		scrollPaneLeft.setViewportView(jTableLeft);

		jTableLeft.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					textField.setText("1");
				}
			}
		});

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rightPanel.setBounds(550, 0, 336, 404);
		contentPanel.add(rightPanel);

		JScrollPane scrollPaneRight = new JScrollPane();
		scrollPaneRight.setBounds(10, 51, 316, 353);
		rightPanel.add(scrollPaneRight);

		jTableRight = new JTable();
		scrollPaneRight.setViewportView(jTableRight);

		JLabel importIdLabel = new JLabel("Import ID: " + importId);
		importIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		importIdLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		importIdLabel.setBounds(0, 0, 336, 52);
		rightPanel.add(importIdLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 403, 886, 60);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(null);

		Color tcolor = Color.decode("#5d7b6f");
		leftPanel.setBackground(tcolor);
		Color pcolor = Color.decode("#A4C3A2");
		rightPanel.setBackground(pcolor);
		Color bcolor = Color.decode("#EAE7D6");
		buttonPane.setBackground(bcolor);

		JButton okButton = new JButton("OK");
		okButton.setBounds(693, 20, 78, 30);
		buttonPane.add(okButton);
		okButton.addActionListener(e -> {
			ImportDetailsDAO importDetailsDAO = new ImportDetailsDAO();
			DefaultTableModel model = (DefaultTableModel) jTableRight.getModel();

			int rowCount = model.getRowCount();
			List<Object[]> rowData = new ArrayList<Object[]>();
			for (int i = 0; i < rowCount; i++) {
				ImportDetailsModel importDetailsModel = new ImportDetailsModel();
				importDetailsModel.setImportid(importId);
				importDetailsModel.setImportdetailid(ImportDetailsDAO.generateUniqueImportDetailId());
				importDetailsModel.setProductd((int) model.getValueAt(i, 0));
				importDetailsModel.setQuantity((int) model.getValueAt(i, 3));
				importDetailsModel.setUnitprice((float) model.getValueAt(i, 4));

				int rowsAffected = importDetailsDAO.insert(importDetailsModel);
				if (rowsAffected > 0) {
					rowData.add(new Object[] { importDetailsModel.getProductd(), model.getValueAt(i, 1),
							importDetailsModel.getQuantity(), importDetailsModel.getUnitprice(),
							importDetailsModel.getUnitprice() });
				}
			}
			if (rowData.size() > 0) {
				try {
					PDF.createPurchaseInvoice(importId, rowData, customerInfo[0], customerInfo[1], customerInfo[2]);
					JOptionPane.showMessageDialog(this, "Đơn hàng đã được nhập", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					ImportView importView = new ImportView();
					importView.updateTotalLabel(importId);
				} catch (DocumentException | IOException ex) {
					JOptionPane.showMessageDialog(this, "Lỗi khi tạo hóa đơn: " + ex.getMessage(), "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
				dispose();

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
	}
}