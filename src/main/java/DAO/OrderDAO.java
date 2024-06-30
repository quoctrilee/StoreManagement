package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.OrdersModel;
import JDBC.JDBC;

public class OrderDAO implements DAO<OrdersModel> {

	@Override
	public int insert(OrdersModel order) {
		String sql = "INSERT INTO Orders (order_id, customer_id, order_date, status, total_amount) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, order.getOrderid());
			stmt.setInt(2, order.getCustomerid());
			stmt.setDate(3, new Date(order.getOdersdate().getTime()));
			stmt.setString(4, order.getStatus());
			stmt.setFloat(5, order.getTotalAmount()); // Set total amount
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(OrdersModel order) {
		String sql = "UPDATE Orders SET customer_id = ?, order_date = ?, status = ?, total_amount = ? WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, order.getCustomerid());
			stmt.setDate(2, new Date(order.getOdersdate().getTime()));
			stmt.setString(3, order.getStatus());
			stmt.setFloat(4, order.getTotalAmount()); // Set total amount
			stmt.setInt(5, order.getOrderid());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int orderid) {
		String sql = "DELETE FROM Orders WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderid);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	 @Override
	    public ArrayList<OrdersModel> selectAll() {
	        ArrayList<OrdersModel> orders = new ArrayList<>();
	        String sql = "SELECT order_id, customer_id, order_date, status, total_amount FROM Orders ORDER BY order_date DESC";
	        try (Connection conn = JDBC.getconConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int orderid = rs.getInt("order_id");
	                int customerid = rs.getInt("customer_id");
	                Date orderdate = rs.getDate("order_date");
	                String status = rs.getString("status");
	                float totalAmount = rs.getFloat("total_amount"); // Retrieve total amount
	                OrdersModel order = new OrdersModel(orderid, customerid, orderdate, status, totalAmount);
	                orders.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orders;
	    }


	@Override
	public OrdersModel selectById(int orderid) {
		String sql = "SELECT order_id, customer_id, order_date, status, total_amount FROM Orders WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderid);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int customerid = rs.getInt("customer_id");
					Date ordersdate = rs.getDate("order_date");
					String status = rs.getString("status");
					float totalAmount = rs.getFloat("total_amount"); // Retrieve total amount
					return new OrdersModel(orderid, customerid, ordersdate, status, totalAmount);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Integer> getAllOrderIDs() {
		ArrayList<Integer> orderIDs = new ArrayList<>();
		String sql = "SELECT order_id FROM Orders";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				orderIDs.add(rs.getInt("order_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderIDs;
	}

	public int updateTotalAmountById(int orderId, float newTotalAmount) {
		String sql = "UPDATE Orders SET total_amount = ? WHERE order_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setFloat(1, newTotalAmount);
			stmt.setInt(2, orderId);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
