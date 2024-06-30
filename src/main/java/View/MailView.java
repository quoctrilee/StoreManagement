package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controller.MailController;
import DAO.LoginDAO;
import Model.LoginModel;

public class MailView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private String email;
	private String password;

	public MailView(String email, String password) {
		this.email = email;
		this.password = password;
		setBounds(100, 100, 500, 350);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã xác nhận đã được gửi về mail của bạn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(28, 22, 412, 47);
		add(lblNewLabel);

		JLabel lblVuiLngNhp = new JLabel("Vui lòng nhập lại mã để hoàn tất đăng ký.");
		lblVuiLngNhp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVuiLngNhp.setBounds(28, 51, 412, 47);
		add(lblVuiLngNhp);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(28, 99, 177, 28);
		add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBounds(215, 99, 91, 28);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String otp = textField.getText();
				if (MailController.verifyOTP(email, otp)) {
					LoginModel loginModel = new LoginModel(email, password);
					LoginDAO.getInstance().insert(loginModel);
					JOptionPane.showMessageDialog(null, "Đăng ký thành công!");

					setVisible(false);
					LoginView loginView = new LoginView();
					loginView.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Mã OTP không đúng. Vui lòng thử lại.");
				}
			}
		});
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Không nhận được OTP?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(28, 137, 118, 21);
		add(lblNewLabel_1);
		
		JLabel reSendlb = new JLabel("<html><a style='text-decoration: underline; color: blue;' href='#'>Gửi lại</a></html>");
		reSendlb.setFont(new Font("Tahoma", Font.PLAIN, 12));
		reSendlb.setBounds(153, 137, 52, 21);
		reSendlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				MailController.sendOTP(email);
			}
		});
		add(reSendlb);
	}
}
