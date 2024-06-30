package View;

import java.awt.BorderLayout;
import java.awt.Color;

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
import Controller.CustomerController;
import Controller.DateController;
import Controller.OrderController;
import DAO.CustomersDAO;
import DAO.OrderConDAO;
import DAO.OrderDAO;
import Model.CustomersModel;
import Model.OrdersModel;

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
import java.awt.Desktop;

public class OrderView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField AddressField;
	private JTextField statusField;
	private OrderController orderController;
	private static JLabel totallable1; // Add this line

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OrderView dialog = new OrderView(new OrderController(new JPanel(), new JTable()));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public OrderView() {

	}

	public OrderView(OrderController orderController) {
		this.orderController = orderController;
		this.totallable1 = new JLabel();
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

		JPanel cuspanel = new JPanel();
		Color tcolor = Color.decode("#A4C3A2");
		cuspanel.setBackground(tcolor);
		cuspanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cuspanel.setBounds(0, 0, 487, 202);
		contentPanel.add(cuspanel);
		cuspanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New Order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 487, 51);
		cuspanel.add(lblNewLabel);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		nameLabel.setBounds(70, 46, 100, 28);
		cuspanel.add(nameLabel);

		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		phoneLabel.setBounds(70, 80, 100, 28);
		cuspanel.add(phoneLabel);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		addressLabel.setBounds(70, 118, 100, 28);
		cuspanel.add(addressLabel);

		nameField = new JTextField();
		nameField.setFont(new Font("Arial", Font.PLAIN, 15));
		nameField.setBounds(193, 46, 222, 28);
		cuspanel.add(nameField);
		nameField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setFont(new Font("Arial", Font.PLAIN, 15));
		phoneField.setColumns(10);
		phoneField.setBounds(193, 81, 222, 28);
		cuspanel.add(phoneField);

		AddressField = new JTextField();
		AddressField.setFont(new Font("Arial", Font.PLAIN, 15));
		AddressField.setColumns(10);
		AddressField.setBounds(193, 119, 222, 28);
		cuspanel.add(AddressField);

		JPanel panel = new JPanel();
		panel.setBackground(tcolor);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 202, 487, 257);
		panel.setLayout(null);

		JLabel cusIDlabel = new JLabel("CustomerID");
		cusIDlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cusIDlabel.setFont(new Font("Arial", Font.PLAIN, 17));
		cusIDlabel.setBounds(73, 16, 100, 28);
		panel.add(cusIDlabel);

		JLabel orderIDlabel = new JLabel("OrderID");
		orderIDlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		orderIDlabel.setFont(new Font("Arial", Font.PLAIN, 17));
		orderIDlabel.setBounds(73, 54, 100, 28);
		panel.add(orderIDlabel);

		JLabel dateLable = new JLabel("Date");
		dateLable.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLable.setFont(new Font("Arial", Font.PLAIN, 17));
		dateLable.setBounds(73, 92, 100, 28);
		panel.add(dateLable);

		JLabel statusLabel = new JLabel("Status");
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		statusLabel.setBounds(73, 130, 100, 25);
		panel.add(statusLabel);

		JButton orderbtn = new JButton("Order");
		orderbtn.setFont(new Font("Arial", Font.PLAIN, 13));
		orderbtn.setBounds(330, 211, 85, 25);
		panel.add(orderbtn);

		JLabel cusIDlabel_1 = new JLabel("");
		cusIDlabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		cusIDlabel_1.setBounds(198, 16, 227, 28);
		panel.add(cusIDlabel_1);

		JLabel orderIDlable1 = new JLabel("");
		orderIDlable1.setFont(new Font("Arial", Font.BOLD, 16));
		orderIDlable1.setBounds(198, 54, 227, 28);
		panel.add(orderIDlable1);

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
						String pdfFilePath = "target/pdf/" + String.valueOf(orderIDlable1.getText()) + ".pdf";
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

		JLabel dateLable1 = new JLabel("");
		dateLable1.setFont(new Font("Arial", Font.BOLD, 16));
		dateLable1.setBounds(198, 92, 227, 28);
		panel.add(dateLable1);

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

			// Kiểm tra nếu tên khách hàng và số điện thoại bị trống
			if (name.isEmpty() || phone.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Customer name and phone cannot be empty.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return; // Dừng lại nếu trường tên hoặc số điện thoại bị trống
			}

			CustomersDAO customersDAO = new CustomersDAO();
			int customerID = CustomersDAO.getID(name, phone);

			if (customerID != 0) {
				cusIDlabel_1.setText(Integer.toString(customerID));
			} else {
				CustomerController controller = new CustomerController();
				int newID = controller.generateUniqueCustomerID();
				cusIDlabel_1.setText(Integer.toString(newID));
				CustomersModel newCustomer = new CustomersModel(newID, name, address, phone);
				customersDAO.insert(newCustomer);
			}

			int newOrderID = orderController.generateUniqueOrderID();
			orderIDlable1.setText(Integer.toString(newOrderID));

			dateLable1.setText(new DateController().getFormattedCurrentDate("dd/MM/yyyy"));

			contentPanel.add(panel);
			contentPanel.revalidate();
			contentPanel.repaint();
		});

		createBtn.setFont(new Font("Arial", Font.PLAIN, 13));
		createBtn.setBounds(330, 157, 85, 28);
		cuspanel.add(createBtn);

		orderbtn.addActionListener(e -> {
			try {
				int orderID = Integer.parseInt(orderIDlable1.getText());
				int cusid = Integer.parseInt(cusIDlabel_1.getText());
				String dateStr = DateController.getFormattedCurrentDate("yyyy-MM-dd");
				Date date = Date.valueOf(dateStr);
				String status = statusField.getText();
				float total = 0;

				OrdersModel ordersModel = new OrdersModel(orderID, cusid, date, status, total);
				OrderDAO orderDao = new OrderDAO();
				orderDao.insert(ordersModel);

				OrderConView orderConView = new OrderConView(orderID, getCustomerOrSupplierInfo());
				orderConView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				orderConView.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this,
						"An error occurred while creating the order. Please check the input data.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	public void updateTotalLabel(int orderId) {
		OrderConDAO orderConDAO = new OrderConDAO();
		float total = orderConDAO.total(orderId);
		String totals = String.valueOf(total);
		totallable1.setText(totals);
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.updateTotalAmountById(orderId, total);
	}

	public String[] getCustomerOrSupplierInfo() {
		String[] info = new String[3];
		info[0] = nameField.getText();
		info[1] = phoneField.getText();
		info[2] = AddressField.getText();
		return info;
	}

	// Method to view PDF
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
