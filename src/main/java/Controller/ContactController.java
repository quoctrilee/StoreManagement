package Controller;

import View.ContactView;
import javax.swing.JOptionPane;
import DAO.LoginDAO;

public class ContactController {

	private ContactView contactView;

	public ContactController(ContactView contactView) {
		this.contactView = contactView;
		initController();
	}

	private void initController() {
		contactView.getChangeButton().addActionListener(e -> changePassword());
	}

	private void changePassword() {
		String currentPass = contactView.getPasswordFieldText().getText();
		if (currentPass.isEmpty()) {
			JOptionPane.showMessageDialog(contactView, "Mật khẩu không được để trống.");
		} else {
			LoginDAO loginDAO = new LoginDAO();
			if (loginDAO.Pass(currentPass)) {
				String newPass = contactView.getnewPassField().getText();
				if (newPass.isEmpty()) {
					JOptionPane.showMessageDialog(contactView, "Mật khẩu mới không được để trống.");
				} else {
					int result = loginDAO.changePass(currentPass, newPass);
					if (result > 0) {
						JOptionPane.showMessageDialog(contactView, "Đổi mật khẩu thành công");
					} else {
						JOptionPane.showMessageDialog(contactView, "Đổi mật khẩu thất bại");
					}
				}
			} else {
				JOptionPane.showMessageDialog(contactView, "Sai mật khẩu hiện tại");
			}
		}
	}
}
