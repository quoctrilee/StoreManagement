package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import JDBC.JDBC;
import Model.LoginModel;

public class LoginController {
	private LoginModel loginModel;

	public LoginController(LoginModel loginModel) {
		this.loginModel = loginModel;
	}

	public boolean login() {
		Connection conn = JDBC.getconConnection();
		String sql = "SELECT username, password  from login where username = ? AND password = ? ";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, loginModel.getUsername());
			pst.setString(2, loginModel.getPassword());
			ResultSet rs = pst.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	

}
