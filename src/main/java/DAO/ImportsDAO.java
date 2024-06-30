package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.ImportsModel;
import JDBC.JDBC;

public class ImportsDAO implements DAO<ImportsModel> {

	@Override
	public int insert(ImportsModel importModel) {
		String sql = "INSERT INTO Imports (import_id, supplier_id, import_date, total_amount, status) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importModel.getImportid());
			stmt.setInt(2, importModel.getSuppliersid());
			stmt.setDate(3, importModel.getImportdate());
			stmt.setFloat(4, importModel.getTotal_price());
			stmt.setString(5, importModel.getStatus());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(ImportsModel importModel) {
		String sql = "UPDATE Imports SET supplier_id = ?, import_date = ?, total_amount = ?, status = ? WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importModel.getSuppliersid());
			stmt.setDate(2, importModel.getImportdate());
			stmt.setFloat(3, importModel.getTotal_price());
			stmt.setString(4, importModel.getStatus());
			stmt.setInt(5, importModel.getImportid());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int importid) {
		String sql = "DELETE FROM Imports WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importid);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	 @Override
	    public ArrayList<ImportsModel> selectAll() {
	        ArrayList<ImportsModel> imports = new ArrayList<>();
	        String sql = "SELECT import_id, supplier_id, import_date, status, total_amount FROM Imports ORDER BY import_date DESC";
	        try (Connection conn = JDBC.getconConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int importid = rs.getInt("import_id");
	                int supplier = rs.getInt("supplier_id");
	                Date importdate = rs.getDate("import_date");
	                String status = rs.getString("status");
	                float total_amount = rs.getFloat("total_amount");
	                ImportsModel importModel = new ImportsModel(importid, supplier, importdate, status, total_amount);
	                imports.add(importModel);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return imports;
	    }

	@Override
	public ImportsModel selectById(int importid) {
		String sql = "SELECT import_id, supplier_id, import_date,status, total_amount FROM Imports WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, importid);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int supplier = rs.getInt("supplier_id");
					Date importdate = rs.getDate("import_date");
					String status = rs.getString("status");
					float total_amount = rs.getFloat("total_amount");
					return new ImportsModel(importid, supplier, importdate, status, total_amount);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Integer> getAllImportIDs() {
		ArrayList<Integer> importIDs = new ArrayList<>();
		String sql = "SELECT import_id FROM Imports";
		try (Connection conn = JDBC.getconConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				importIDs.add(rs.getInt("import_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return importIDs;
	}

	public int updateTotalAmountById(int importid, float newTotalAmount) {
		String sql = "UPDATE Imports SET total_amount = ? WHERE import_id = ?";
		try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setFloat(1, newTotalAmount);
			stmt.setInt(2, importid);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
