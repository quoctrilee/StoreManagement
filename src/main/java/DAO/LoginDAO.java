package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.JDBC;
import Model.LoginModel;

public class LoginDAO implements DAO<LoginModel> {

    public static LoginDAO getInstance() {
        return new LoginDAO();
    }

    @Override
    public int insert(LoginModel t) {
        int ketqua = 0;
        Connection conn = JDBC.getconConnection();
        String sql = "Insert into Login (username, password) Values (?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, t.getUsername());
            statement.setString(2, t.getPassword());
            ketqua = statement.executeUpdate();
            statement.close();
            JDBC.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int update(LoginModel t) {
        int ketqua = 0;
        Connection conn = JDBC.getconConnection();
        String sql = "UPDATE login SET password = ? WHERE username = ? ";
        try {
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, t.getPassword());
            prst.setString(2, t.getUsername());
            ketqua = prst.executeUpdate();
            prst.close();
            JDBC.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int delete(int t) {
        int ketqua = 0;
        Connection conn = JDBC.getconConnection();
        String sql = "DELETE from login WHERE username = ?";
        try {
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1, t);
            ketqua = prst.executeUpdate();
            prst.close();
            JDBC.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public LoginModel selectById(int t) {
        return null;
    }

    @Override
    public ArrayList<LoginModel> selectAll() {
        ArrayList<LoginModel> arr = new ArrayList<>();
        String sql = "SELECT * FROM LOGIN";
        Connection conn = JDBC.getconConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String user = rs.getString("username");
                String pass = rs.getString("password");
                LoginModel loginModel = new LoginModel(user, pass);
                arr.add(loginModel);
            }
            preparedStatement.close();
            JDBC.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public int changePass(String currentPass, String newPass) {
        int rs = 0;
        String sql = "UPDATE login SET password = ? WHERE password = ?";
        Connection conn = JDBC.getconConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, currentPass);
            rs = preparedStatement.executeUpdate();
            preparedStatement.close();
            JDBC.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean Pass(String pass) {
        String sql = "SELECT * FROM login WHERE password = ?";
        Connection conn = JDBC.getconConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, pass);
            ResultSet rs = preparedStatement.executeQuery();
            boolean exists = rs.next();
            rs.close();
            preparedStatement.close();
            JDBC.closeConnection(conn);
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isEmailExists(String email) {
        String sql = "SELECT * FROM login WHERE username = ?";
        Connection conn = JDBC.getconConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            boolean exists = rs.next();
            rs.close();
            preparedStatement.close();
            JDBC.closeConnection(conn);
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
