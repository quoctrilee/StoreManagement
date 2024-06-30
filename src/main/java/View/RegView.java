package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.MailController;
import DAO.LoginDAO;

public class RegView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userField;
	private JTextField passField;
	private JTextField repassField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegView frame = new RegView();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon icon = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\logo.jpg");

		// Chỉnh sửa kích thước hình ảnh nếu cần thiết
		Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		// Tạo một ImageIcon mới từ hình ảnh đã được chỉnh sửa
		ImageIcon scaledIcon = new ImageIcon(image);

		// Đặt icon cho JFrame
		setIconImage(scaledIcon.getImage());
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Đăng ký");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblNewLabel.setBounds(0, 0, 486, 71);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(47, 84, 112, 21);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(47, 134, 112, 21);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(47, 189, 131, 21);
		contentPane.add(lblNewLabel_1_2);

		userField = new JTextField();
		userField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userField.setBounds(179, 81, 236, 32);
		contentPane.add(userField);
		userField.setColumns(10);

		passField = new JTextField();
		passField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passField.setColumns(10);
		passField.setBounds(179, 134, 236, 32);
		contentPane.add(passField);

		repassField = new JTextField();
		repassField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		repassField.setColumns(10);
		repassField.setBounds(179, 184, 236, 32);
		contentPane.add(repassField);

		JButton regbtn = new JButton("Đăng ký");
		regbtn.setBounds(186, 251, 118, 32);
		contentPane.add(regbtn);

		regbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userField.getText();
				String password = passField.getText();
				String repassword = repassField.getText();

				if (username.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Tất cả các trường không được để trống", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else if (!username.endsWith("@gmail.com")) {
					JOptionPane.showMessageDialog(contentPane, "Sai định dạng, đây không phải là một địa chỉ email",
							"Lỗi", JOptionPane.ERROR_MESSAGE);
				} else if (!password.equals(repassword)) {
					JOptionPane.showMessageDialog(contentPane, "Nhập lại mật khẩu không đúng", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else if (LoginDAO.getInstance().isEmailExists(username)) {
					JOptionPane.showMessageDialog(contentPane, "Email đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
				} else {

					MailView mailView = new MailView(username, password);
					setContentPane(mailView);
					revalidate();
					MailController.sendOTP(username);
				}
			}
		});
	}
}
