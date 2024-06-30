package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.LoginController;
import Controller.MailController;
import DAO.LoginDAO;
import Model.LoginModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {

		// Khai báo và khởi tạo ImageIcon từ đường dẫn đến tệp hình ảnh
		ImageIcon icon = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\logo.jpg");

		// Chỉnh sửa kích thước hình ảnh nếu cần thiết
		Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		// Tạo một ImageIcon mới từ hình ảnh đã được chỉnh sửa
		ImageIcon scaledIcon = new ImageIcon(image);

		// Đặt icon cho JFrame
		setIconImage(scaledIcon.getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 465);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Load images
		Image leftImage = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\loginleft.jpg")
				.getImage();
		Image rightImage = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\rightlogin.jpg")
				.getImage();

		// Create custom panels with background images
		ImagePanel leftPanel = new ImagePanel(leftImage);
		leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftPanel.setBounds(0, 0, 369, 443);
		contentPane.add(leftPanel);

		ImagePanel rightPanel = new ImagePanel(rightImage);
		rightPanel.setBounds(369, 0, 337, 443);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);

		JLabel userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		userLabel.setForeground(Color.WHITE);
		userLabel.setBounds(45, 94, 108, 30);
		rightPanel.add(userLabel);

		JLabel passLabel = new JLabel("Password");
		passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		passLabel.setForeground(Color.WHITE);
		passLabel.setBounds(45, 174, 108, 30);
		rightPanel.add(passLabel);

		textField = new JTextField();
		textField.setBounds(45, 134, 200, 30);
		rightPanel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(45, 214, 200, 30);
		rightPanel.add(passwordField);

		JButton logBtn = new JButton("Đăng nhập");
		logBtn.setBounds(150, 261, 95, 20);

		JButton regBtn = new JButton("Đăng ký");
		regBtn.setBounds(45, 261, 90, 20);
		rightPanel.add(regBtn);

		logBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginModel loginModel = new LoginModel(textField.getText(), new String(passwordField.getPassword()));
				LoginController controller = new LoginController(loginModel);
				if (controller.login()) {
					Home home = new Home();
					dispose();
					home.setVisible(true); // Make the Home frame visible
				} else {
					JOptionPane.showMessageDialog(rightPanel, "Login Failed");
				}
			}
		});
		rightPanel.add(logBtn);

		JLabel forgetpass = new JLabel(
				"<html><a style='text-decoration: underline; color: blue;' href='#'>Quên mật khẩu?</a></html>");
		forgetpass.setBounds(155, 300, 200, 20);
		rightPanel.add(forgetpass);
		forgetpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String email = JOptionPane.showInputDialog(null, "Nhập địa chỉ email của bạn:");
				if (email != null && !email.isEmpty()) {
					if (LoginDAO.getInstance().isEmailExists(email)) {
						MailController.sendOTP(email);
						boolean otpVerified = false;
						while (!otpVerified) {
							String otp = JOptionPane.showInputDialog(null, "Nhập mã OTP đã được gửi về email của bạn:");
							if (otp != null && !otp.isEmpty() && MailController.verifyOTP(email, otp)) {
								otpVerified = true;
								String newPassword = JOptionPane.showInputDialog(null, "Nhập mật khẩu mới:");
								LoginModel lgLoginModel = new LoginModel(email, newPassword);
								LoginDAO.getInstance().update(lgLoginModel);
								JOptionPane.showMessageDialog(null, "Mật khẩu đã được cập nhật thành công!");
							} else {
								JOptionPane.showMessageDialog(null, "Mã OTP không đúng. Vui lòng thử lại.");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Địa chỉ email không tồn tại trong hệ thống.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ email.");
				}
			}
		});

	}

	// Custom JPanel class to paint background image
	class ImagePanel extends JPanel {
		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}

}
