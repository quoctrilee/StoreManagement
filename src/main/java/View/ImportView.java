package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import Controller.ImportController;
import Controller.DateController;
import DAO.SuppliersDAO;
import DAO.ImportConDAO;
import DAO.ImportsDAO;
import Model.SuppliersModel;
import Model.ImportsModel;

import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

public class ImportView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField AddressField;
	private JTextField statusField;
	private ImportController importController;
	private static JLabel totallable1; // Add this line

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ImportView dialog = new ImportView(new ImportController(new JPanel(), new JTable()));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public ImportView() {

	}

	public ImportView(ImportController importController) {
		this.importController = importController;
		this.totallable1 = new JLabel();
		setLocationRelativeTo(null);
		setBounds(100, 100, 501, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		ImageIcon icon = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\logo.jpg");

		// Chỉnh sửa kích thước hình ảnh nếu cần thiết
		Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		// Tạo một ImageIcon mới từ hình ảnh đã được chỉnh sửa
		ImageIcon scaledIcon = new ImageIcon(image);

		// Đặt icon cho JFrame
		setIconImage(scaledIcon.getImage());
		setLocationRelativeTo(null);

		JPanel supplierPanel = new JPanel();
		Color pcolor = Color.decode("#A4C3A2");
		supplierPanel.setBackground(pcolor);
		supplierPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		supplierPanel.setBounds(0, 0, 487, 202);
		contentPanel.add(supplierPanel);
		supplierPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New Import");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 487, 51);
		supplierPanel.add(lblNewLabel);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		nameLabel.setBounds(70, 46, 100, 28);
		supplierPanel.add(nameLabel);

		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		phoneLabel.setBounds(70, 80, 100, 28);
		supplierPanel.add(phoneLabel);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		addressLabel.setBounds(70, 118, 100, 28);
		supplierPanel.add(addressLabel);

		nameField = new JTextField();
		nameField.setFont(new Font("Arial", Font.PLAIN, 15));
		nameField.setBounds(193, 46, 222, 28);
		supplierPanel.add(nameField);
		nameField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setFont(new Font("Arial", Font.PLAIN, 15));
		phoneField.setColumns(10);
		phoneField.setBounds(193, 81, 222, 28);
		supplierPanel.add(phoneField);

		AddressField = new JTextField();
		AddressField.setFont(new Font("Arial", Font.PLAIN, 15));
		AddressField.setColumns(10);
		AddressField.setBounds(193, 119, 222, 28);
		supplierPanel.add(AddressField);

		JPanel panel = new JPanel();
		panel.setBackground(pcolor);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 202, 487, 257);
		panel.setLayout(null);

		JLabel supplierIDLabel = new JLabel("SupplierID");
		supplierIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		supplierIDLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		supplierIDLabel.setBounds(73, 16, 100, 28);
		panel.add(supplierIDLabel);

		JLabel importIDLabel = new JLabel("ImportID");
		importIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		importIDLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		importIDLabel.setBounds(73, 54, 100, 28);
		panel.add(importIDLabel);

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		dateLabel.setBounds(73, 92, 100, 28);
		panel.add(dateLabel);

		JLabel statusLabel = new JLabel("Status");
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		statusLabel.setBounds(73, 130, 100, 25);
		panel.add(statusLabel);

		JButton importBtn = new JButton("Import");
		importBtn.setFont(new Font("Arial", Font.PLAIN, 13));
		importBtn.setBounds(330, 211, 85, 25);
		panel.add(importBtn);

		JLabel supplierIDLabel_1 = new JLabel("");
		supplierIDLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		supplierIDLabel_1.setBounds(198, 16, 227, 28);
		panel.add(supplierIDLabel_1);

		JLabel importIDLabel1 = new JLabel("");
		importIDLabel1.setFont(new Font("Arial", Font.BOLD, 16));
		importIDLabel1.setBounds(198, 54, 227, 28);
		panel.add(importIDLabel1);

		JLabel dateLabel1 = new JLabel("");
		dateLabel1.setFont(new Font("Arial", Font.BOLD, 16));
		dateLabel1.setBounds(198, 92, 227, 28);
		panel.add(dateLabel1);

		statusField = new JTextField();
		statusField.setFont(new Font("Arial", Font.PLAIN, 15));
		statusField.setColumns(10);
		statusField.setBounds(198, 130, 41, 28);
		panel.add(statusField);

		JLabel lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalPrice.setFont(new Font("Arial", Font.PLAIN, 17));
		lblTotalPrice.setBounds(73, 167, 100, 25);
		panel.add(lblTotalPrice);

		totallable1.setFont(new Font("Arial", Font.BOLD, 16)); // Add this line
		totallable1.setBounds(198, 167, 227, 28); // Add this line
		panel.add(totallable1); // Add this line

		JButton createBtn = new JButton("Create");
		createBtn.addActionListener(e -> {
			String name = nameField.getText();
			String phone = phoneField.getText();
			String address = AddressField.getText();

			// Kiểm tra nếu tên nhà cung cấp và số điện thoại bị trống
			if (name.isEmpty() || phone.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Supplier name and phone cannot be empty.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return; // Dừng lại nếu trường tên hoặc số điện thoại bị trống
			}

			SuppliersDAO supplierDAO = new SuppliersDAO();
			int supplierID = supplierDAO.getID(name, phone);

			if (supplierID != 0) {
				supplierIDLabel_1.setText(Integer.toString(supplierID));
			} else {
				int newID = importController.generateUniqueImportID();
				supplierIDLabel_1.setText(Integer.toString(newID));
				SuppliersModel newSupplier = new SuppliersModel(newID, name, address, phone);
				supplierDAO.insert(newSupplier);
			}

			int newImportID = importController.generateUniqueImportID();
			importIDLabel1.setText(Integer.toString(newImportID));

			dateLabel1.setText(new DateController().getFormattedCurrentDate("dd/MM/yyyy"));

			contentPanel.add(panel);
			contentPanel.revalidate();
			contentPanel.repaint();
		});

		createBtn.setFont(new Font("Arial", Font.PLAIN, 13));
		createBtn.setBounds(330, 157, 85, 28);
		supplierPanel.add(createBtn);

		importBtn.addActionListener(e -> {
			try {
				int importID = Integer.parseInt(importIDLabel1.getText());
				int supplierID = Integer.parseInt(supplierIDLabel_1.getText());
				String dateStr = DateController.getFormattedCurrentDate("yyyy-MM-dd");
				Date date = Date.valueOf(dateStr);
				String status = statusField.getText();
				float total = 0;

				ImportsModel importsModel = new ImportsModel(importID, supplierID, date, status, total);
				ImportsDAO importDAO = new ImportsDAO();
				importDAO.insert(importsModel);

				ImportConView importConView = new ImportConView(importID, getCustomerOrSupplierInfo());
				importConView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				importConView.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this,
						"An error occurred while creating the import. Please check the input data.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-10, 460, 497, 53);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.setBounds(290, 17, 70, 26);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(e -> {
					int response = JOptionPane.showConfirmDialog(this, "Do you want to view the generated PDF?",
							"View PDF", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
						String pdfFilePath = "target/pdf/" + String.valueOf(importIDLabel1.getText()) + ".pdf";
						viewPDF(pdfFilePath);

					}
					dispose();
				});
				getRootPane().setDefaultButton(okButton);

			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(388, 17, 70, 26);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e -> {
					dispose();
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	public void updateTotalLabel(int importId) {
		ImportConDAO importConDAO = new ImportConDAO();
		float total = importConDAO.total(importId);
		String totals = String.valueOf(total);
		totallable1.setText(totals);

		ImportsDAO importDAO = new ImportsDAO();
		importDAO.updateTotalAmountById(importId, total);
	}

	public String[] getCustomerOrSupplierInfo() {
		String[] info = new String[3];
		info[0] = nameField.getText();
		info[1] = phoneField.getText();
		info[2] = AddressField.getText();
		return info;
	}

	private void viewPDF(String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				Desktop.getDesktop().open(file);
			} else {
				JOptionPane.showMessageDialog(this, "The PDF file does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "An error occurred while opening the PDF file.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
