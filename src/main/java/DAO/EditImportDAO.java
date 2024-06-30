package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.JDBC;
import Model.ImportDetailsModel;
import Model.ProductsModel;

public class EditImportDAO {

	// Lấy danh sách chi tiết nhập hàng theo ID nhập hàng
	public List<ImportDetailsModel> selectByImportId(int importId) {
		List<ImportDetailsModel> importDetailsList = new ArrayList<>();
		String sql = "SELECT import_detail_id, import_id, product_id, quantity, unit_price FROM ImportDetails WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					ImportDetailsModel importDetails = new ImportDetailsModel();
					importDetails.setImportdetailid(rs.getInt("import_detail_id"));
					importDetails.setImportid(rs.getInt("import_id"));
					importDetails.setProductd(rs.getInt("product_id"));
					importDetails.setQuantity(rs.getInt("quantity"));
					importDetails.setUnitprice(rs.getFloat("unit_price"));
					importDetailsList.add(importDetails);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return importDetailsList;
	}

	// Lấy thông tin sản phẩm theo ID sản phẩm
	public ProductsModel selectById(int productId) {
		String sql = "SELECT product_id, name, description, price, quantity_available, category_id FROM Products WHERE product_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, productId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					String description = rs.getString("description");
					float price = rs.getFloat("price");
					int quantity = rs.getInt("quantity_available");
					int categoryId = rs.getInt("category_id");
					return new ProductsModel(productId, name, description, price, quantity, categoryId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Xóa chi tiết nhập hàng theo ID nhập hàng
	public boolean deleteImportDetailsByImportId(int importId) {
		String sql = "DELETE FROM ImportDetails WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importId);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0; // Trả về true nếu có hàng bị xóa
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Trả về false nếu có lỗi xảy ra
		}
	}
}
