package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.ProductsModel;
import JDBC.JDBC;

public class ImportConDAO {

	public List<ProductsModel> getAllProducts() {
		List<ProductsModel> products = new ArrayList<>();
		String sql = "SELECT product_id, name, description, price, quantity_available FROM Products";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				int quantity = rs.getInt("quantity_available");
				ProductsModel product = new ProductsModel(productId, name, description, price, quantity, 0); // CategoryID
																												// set
																												// to 0
																												// as
																												// it's
																												// not
																												// needed
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	// Tính tổng số tiền của chi tiết nhập hàng theo ID nhập hàng
	public float total(int importId) {
		List<Float> unitPrices = new ArrayList<>();
		try (Connection connection = JDBC.getconConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT unit_price FROM ImportDetails WHERE import_id = ?")) {
			statement.setInt(1, importId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					float unitPrice = resultSet.getFloat("unit_price");
					unitPrices.add(unitPrice);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		float total = 0;
		for (Float unitPrice : unitPrices) {
			total += unitPrice;
		}
		return total;
	}
}