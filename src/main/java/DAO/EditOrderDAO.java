package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.JDBC;
import Model.OrderDetailsModel;
import Model.ProductsModel;

public class EditOrderDAO {
	// No need to initialize models here as they are just used for fetching data

	public List<OrderDetailsModel> selectByOrderId(int orderId) {
		List<OrderDetailsModel> orderDetailsList = new ArrayList<>();
		String sql = "SELECT order_detail_id, order_id, product_id, quantity, unit_price FROM OrderDetails WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					OrderDetailsModel orderDetails = new OrderDetailsModel();
					orderDetails.setOrderdetailid(rs.getInt("order_detail_id"));
					orderDetails.setOrderid(rs.getInt("order_id"));
					orderDetails.setProductid(rs.getInt("product_id"));
					orderDetails.setQuantity(rs.getInt("quantity"));
					orderDetails.setUnitprice(rs.getFloat("unit_price"));
					orderDetailsList.add(orderDetails);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetailsList;
	}

	public ProductsModel selectById(int productid) {
		String sql = "SELECT product_id, name, description, price, quantity_available, category_id FROM Products WHERE product_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, productid);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					String description = rs.getString("description");
					float price = rs.getFloat("price");
					int quantity = rs.getInt("quantity_available");
					int categoryid = rs.getInt("category_id");
					return new ProductsModel(productid, name, description, price, quantity, categoryid);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteOrderDetailsByOrderId(int orderId) {
		String sql = "DELETE FROM OrderDetails WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderId);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0; // Trả về true nếu có hàng bị xóa
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Trả về false nếu có lỗi xảy ra
		}
	}
}
