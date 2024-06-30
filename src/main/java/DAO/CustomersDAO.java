package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.CustomersModel;
import JDBC.JDBC;

public class CustomersDAO implements DAO<CustomersModel> {

	@Override
	public int insert(CustomersModel customer) {
		String sql = "INSERT INTO Customers (customer_id, name, address, phone) VALUES (?, ?, ?, ?)";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, customer.getCustomerid());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getAddress());
			stmt.setString(4, customer.getPhone());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(CustomersModel customer) {
		String sql = "UPDATE Customers SET name = ?, address = ?, phone = ? WHERE customer_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getAddress());
			stmt.setString(3, customer.getPhone());
			stmt.setInt(4, customer.getCustomerid());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int customerid) {
		String sql = "DELETE FROM Customers WHERE customer_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, customerid);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<CustomersModel> selectAll() {
		ArrayList<CustomersModel> customers = new ArrayList<>();
		String sql = "SELECT customer_id, name, address, phone FROM Customers";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				int customerid = rs.getInt("customer_id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				CustomersModel customer = new CustomersModel(customerid, name, address, phone);
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public CustomersModel selectById(int customerid) {
		String sql = "SELECT customer_id, name, address, phone FROM Customers WHERE customer_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, customerid);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					return new CustomersModel(customerid, name, address, phone);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isNamePhoneExist(String name, String phone) {
		String sql = "SELECT COUNT(*) AS count FROM Customers WHERE name = ? AND phone = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setString(2, phone);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt("count");
					return count > 0; // Trả về true nếu có ít nhất một bản ghi có cùng tên và số điện thoại
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // Trả về false nếu xảy ra lỗi hoặc không tìm thấy bản ghi nào
	}

	public ArrayList<Integer> getAllCustomerIDs() {
		ArrayList<Integer> customerIDs = new ArrayList<>();
		String sql = "SELECT customer_id FROM Customers";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				customerIDs.add(rs.getInt("customer_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerIDs;
	}

	public static int getID(String name, String phone) {
		String sql = "SELECT customer_id FROM Customers WHERE name = ? AND phone = ?";
		Connection conn = JDBC.getconConnection();
		int customerID = 0; // Khởi tạo customerID với giá trị mặc định là 0

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, phone);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customerID = resultSet.getInt("customer_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return customerID;
	}

}
