package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;

public class ProductsView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField IDField;
	private JTextField nameField;
	private JTextField desField;
	private JTextField priceField;
	private JTextField quantityField;
	private JTextField categoriesidField;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProductsView dialog = new ProductsView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProductsView() {

		setBounds(100, 100, 500, 503);
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
		{
			JPanel buttonPane = new JPanel();
			Color bcolor = Color.decode("#EAE7D6");
			buttonPane.setBackground(bcolor);
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setBounds(0, 413, 486, 53);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				okButton = new JButton("OK");
				okButton.setBounds(294, 10, 70, 33);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBounds(388, 10, 70, 33);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		JLabel lblNewLabel = new JLabel("Thêm sản phẩm mới");
		lblNewLabel.setOpaque(true);
		Color tcolor = Color.decode("#5d7b6f");
		lblNewLabel.setBackground(tcolor);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel.setBounds(0, 0, 486, 75);
		contentPanel.add(lblNewLabel);

		JPanel panel = new JPanel();
		Color pcolor = Color.decode("#A4C3A2");
		panel.setBackground(pcolor);
		panel.setBounds(0, 74, 486, 337);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Product ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(73, 23, 105, 34);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(73, 73, 105, 34);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Description");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(73, 117, 105, 34);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Price");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_3.setBounds(73, 161, 105, 34);
		panel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Quantity");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_4.setBounds(73, 205, 105, 34);
		panel.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Categori ID");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_5.setBounds(73, 249, 105, 34);
		panel.add(lblNewLabel_1_5);

		IDField = new JTextField();
		IDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		IDField.setBounds(207, 23, 188, 34);
		panel.add(IDField);
		IDField.setColumns(10);

		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameField.setColumns(10);
		nameField.setBounds(207, 73, 188, 34);
		panel.add(nameField);

		desField = new JTextField();
		desField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		desField.setColumns(10);
		desField.setBounds(207, 117, 188, 34);
		panel.add(desField);

		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceField.setColumns(10);
		priceField.setBounds(207, 161, 188, 34);
		panel.add(priceField);

		quantityField = new JTextField();
		quantityField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		quantityField.setColumns(10);
		quantityField.setBounds(207, 205, 188, 34);
		panel.add(quantityField);

		categoriesidField = new JTextField();
		categoriesidField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		categoriesidField.setColumns(10);
		categoriesidField.setBounds(207, 249, 188, 34);
		panel.add(categoriesidField);
	}

	public JTextField getIDField() {
		return IDField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getDesField() {
		return desField;
	}

	public JTextField getPriceField() {
		return priceField;
	}

	public JTextField getQuantityField() {
		return quantityField;
	}

	public JTextField getCategoriesidField() {
		return categoriesidField;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
