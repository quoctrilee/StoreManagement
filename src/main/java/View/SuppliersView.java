package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SuppliersView extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JButton okButton;
	private JButton cancelButton;

	public SuppliersView() {
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
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

		JLabel lblNewLabel = new JLabel("Supplier Information");
		lblNewLabel.setOpaque(true);
		Color tcolor = Color.decode("#5d7b6f");
		lblNewLabel.setBackground(tcolor);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel.setBounds(0, 0, 434, 44);
		contentPanel.add(lblNewLabel);

		JLabel lblId = new JLabel("Supplier ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(10, 55, 105, 25);
		contentPanel.add(lblId);

		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(125, 55, 294, 25);
		contentPanel.add(idField);
		idField.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(10, 90, 105, 25);
		contentPanel.add(lblName);

		nameField = new JTextField();
		nameField.setBounds(125, 90, 294, 25);
		contentPanel.add(nameField);
		nameField.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(10, 125, 105, 25);
		contentPanel.add(lblAddress);

		addressField = new JTextField();
		addressField.setBounds(125, 125, 294, 25);
		contentPanel.add(addressField);
		addressField.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhone.setBounds(10, 160, 105, 25);
		contentPanel.add(lblPhone);

		phoneField = new JTextField();
		phoneField.setBounds(125, 160, 294, 25);
		contentPanel.add(phoneField);
		phoneField.setColumns(10);

		JPanel buttonPane = new JPanel();
		Color bcolor = Color.decode("#EAE7D6");
		buttonPane.setBackground(bcolor);
		buttonPane.setBounds(0, 218, 434, 35);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);

		okButton = new JButton("OK");
		okButton.setBounds(233, 5, 89, 23);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(332, 5, 89, 23);
		buttonPane.add(cancelButton);
	}

	public JTextField getIdField() {
		return idField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public JTextField getPhoneField() {
		return phoneField;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}