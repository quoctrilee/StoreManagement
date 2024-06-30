package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import DAO.LoginDAO;
import Model.LoginModel;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ContactView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField passwordField;
	private JButton changeButton;
	private JTextField newPassField;

	/**
	 * Create the panel.
	 */
	public ContactView() {
		Color bcolor = Color.decode("#EAE7D6");
		setBackground(bcolor);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Tài khoản - Liên hệ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(122, 23, 632, 78);
		add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(bcolor);

		panel.setBounds(122, 99, 632, 332);
		add(panel);
		panel.setLayout(null);

		LoginDAO loginDAO = new LoginDAO();
		ArrayList<LoginModel> arr = loginDAO.selectAll();
		LoginModel loginModel = new LoginModel();
		if (!arr.isEmpty()) {
			loginModel = arr.get(0);
		}

		JLabel lblNewLabel_1 = new JLabel("Tài khoản: " + loginModel.getUsername());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(117, 29, 400, 43);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("  Mật khẩu cũ: ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(108, 74, 134, 43);
		panel.add(lblNewLabel_1_1);

		passwordField = new JTextField();
		passwordField.setBounds(252, 82, 192, 32);
		panel.add(passwordField);
		passwordField.setColumns(10);

		changeButton = new JButton("đổi mật khẩu");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		changeButton.setBounds(342, 177, 102, 21);
		panel.add(changeButton);

		JLabel lblNewLabel_1_1_1 = new JLabel("Contact Us");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(117, 199, 86, 43);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Gmail: trilq.23it@vku.udn.vn");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(117, 235, 400, 43);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Sđt: 0327176xxx");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(117, 268, 400, 43);
		panel.add(lblNewLabel_1_2_1);

		newPassField = new JTextField();
		newPassField.setColumns(10);
		newPassField.setBounds(252, 135, 192, 32);
		panel.add(newPassField);

		JLabel lblNewLabel_1_1_2 = new JLabel("  Mật khẩu mới: ");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(108, 123, 134, 43);
		panel.add(lblNewLabel_1_1_2);

		JButton logoutbtn = new JButton("Đăng xuất");
		logoutbtn.setBounds(404, 242, 114, 32);
		logoutbtn.addActionListener(e -> {
			Home.logout(); // Call the logout method of Home
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			topFrame.dispose(); // Dispose of the current Home frame

		});
		panel.add(logoutbtn);

	}

	public JTextField getPasswordFieldText() {
		return passwordField;
	}

	public JTextField getnewPassField() {
		return newPassField;
	}

	public JButton getChangeButton() {
		return changeButton;
	}
}
