package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.OrderDetailsModel;
import JDBC.JDBC;

public class OrderDetailsDAO implements DAO<OrderDetailsModel> {

	@Override
	public int insert(OrderDetailsModel orderDetail) {
		String sql = "INSERT INTO OrderDetails (order_detail_id, order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderDetail.getOrderdetailid());
			stmt.setInt(2, orderDetail.getOrderid());
			stmt.setInt(3, orderDetail.getProductid());
			stmt.setInt(4, orderDetail.getQuantity());
			stmt.setFloat(5, orderDetail.getUnitprice());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(OrderDetailsModel orderDetail) {
		String sql = "UPDATE OrderDetails SET product_id = ?, quantity = ?, unit_price = ? WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderDetail.getProductid());
			stmt.setInt(2, orderDetail.getQuantity());
			stmt.setFloat(3, orderDetail.getUnitprice());
			stmt.setInt(4, orderDetail.getOrderid());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int orderId) {
		String sql = "DELETE FROM OrderDetails WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderId);
			System.out.println("ok");
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<OrderDetailsModel> selectAll() {
		ArrayList<OrderDetailsModel> orderDetails = new ArrayList<>();
		String sql = "SELECT order_detail_id, order_id, product_id, quantity, unit_price FROM OrderDetails";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				int orderDetailId = rs.getInt("order_detail_id");
				int orderId = rs.getInt("order_id");
				int productId = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				float unitPrice = rs.getFloat("unit_price");
				OrderDetailsModel orderDetail = new OrderDetailsModel(orderDetailId, orderId, productId, quantity,
						unitPrice);
				orderDetails.add(orderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetails;
	}

	@Override
	public OrderDetailsModel selectById(int orderDetailId) {
		String sql = "SELECT order_detail_id, order_id, product_id, quantity, unit_price FROM OrderDetails WHERE order_detail_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderDetailId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int orderDetailIdDb = rs.getInt("order_detail_id");
					int orderId = rs.getInt("order_id");
					int productId = rs.getInt("product_id");
					int quantity = rs.getInt("quantity");
					float unitPrice = rs.getFloat("unit_price");
					return new OrderDetailsModel(orderDetailIdDb, orderId, productId, quantity, unitPrice);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int generateUniqueOrderDetailId() {
		String sql = "SELECT MAX(order_detail_id) FROM OrderDetails";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1; // Trả về 1 nếu bảng rỗng hoặc có lỗi
	}

	public List<OrderDetailsModel> selectByOrderId(int orderId) {
		List<OrderDetailsModel> orderDetails = new ArrayList<>();
		String sql = "SELECT order_detail_id, order_id, product_id, quantity, unit_price FROM OrderDetails WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int orderDetailId = rs.getInt("order_detail_id");
					int orderIdDb = rs.getInt("order_id");
					int productId = rs.getInt("product_id");
					int quantity = rs.getInt("quantity");
					float unitPrice = rs.getFloat("unit_price");
					orderDetails.add(new OrderDetailsModel(orderDetailId, orderIdDb, productId, quantity, unitPrice));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetails;
	}

}
