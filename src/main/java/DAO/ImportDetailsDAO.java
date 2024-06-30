package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.JDBC;
import Model.ImportDetailsModel;

public class ImportDetailsDAO implements DAO<ImportDetailsModel> {

	@Override
	public int insert(ImportDetailsModel importDetail) {
		String sql = "INSERT INTO ImportDetails (import_detail_id, import_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importDetail.getImportdetailid());
			stmt.setInt(2, importDetail.getImportid());
			stmt.setInt(3, importDetail.getProductd());
			stmt.setInt(4, importDetail.getQuantity());
			stmt.setFloat(5, importDetail.getUnitprice());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(ImportDetailsModel importDetail) {
		String sql = "UPDATE ImportDetails SET product_id = ?, quantity = ?, unit_price = ? WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importDetail.getProductd());
			stmt.setInt(2, importDetail.getQuantity());
			stmt.setFloat(3, importDetail.getUnitprice());
			stmt.setInt(4, importDetail.getImportid());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int importId) {
		String sql = "DELETE FROM ImportDetails WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importId);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<ImportDetailsModel> selectAll() {
		ArrayList<ImportDetailsModel> importDetails = new ArrayList<>();
		String sql = "SELECT import_detail_id, import_id, product_id, quantity, unit_price FROM ImportDetails";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				int importDetailId = rs.getInt("import_detail_id");
				int importId = rs.getInt("import_id");
				int productId = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				float unitPrice = rs.getFloat("unit_price");
				ImportDetailsModel importDetail = new ImportDetailsModel(importDetailId, importId, productId, quantity,
						unitPrice);
				importDetails.add(importDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return importDetails;
	}

	@Override
	public ImportDetailsModel selectById(int importDetailId) {
		String sql = "SELECT import_detail_id, import_id, product_id, quantity, unit_price FROM ImportDetails WHERE import_detail_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importDetailId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int importDetailIdDb = rs.getInt("import_detail_id");
					int importId = rs.getInt("import_id");
					int productId = rs.getInt("product_id");
					int quantity = rs.getInt("quantity");
					float unitPrice = rs.getFloat("unit_price");
					return new ImportDetailsModel(importDetailIdDb, importId, productId, quantity, unitPrice);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int generateUniqueImportDetailId() {
		String sql = "SELECT MAX(import_detail_id) FROM ImportDetails";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1; // Return 1 if the table is empty or an error occurs
	}

	public List<ImportDetailsModel> selectByImportId(int importId) {
		List<ImportDetailsModel> importDetails = new ArrayList<>();
		String sql = "SELECT import_detail_id, import_id, product_id, quantity, unit_price FROM ImportDetails WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int importDetailId = rs.getInt("import_detail_id");
					int importIdDb = rs.getInt("import_id");
					int productId = rs.getInt("product_id");
					int quantity = rs.getInt("quantity");
					float unitPrice = rs.getFloat("unit_price");
					importDetails
							.add(new ImportDetailsModel(importDetailId, importIdDb, productId, quantity, unitPrice));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return importDetails;
	}
}
