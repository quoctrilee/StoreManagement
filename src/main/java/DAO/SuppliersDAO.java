package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.SuppliersModel;
import JDBC.JDBC;

public class SuppliersDAO implements DAO<SuppliersModel> {

	 @Override
	    public int insert(SuppliersModel supplier) {
	        String sql = "INSERT INTO Suppliers (supplier_id, name, address, phone) VALUES (?, ?, ?, ?)";
	        try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, supplier.getSuppliersid());
	            stmt.setString(2, supplier.getName());
	            stmt.setString(3, supplier.getAddress());
	            stmt.setString(4, supplier.getPhone());
	            return stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }

	    @Override
	    public int update(SuppliersModel supplier) {
	        String sql = "UPDATE Suppliers SET name = ?, address = ?, phone = ? WHERE supplier_id = ?";
	        try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, supplier.getName());
	            stmt.setString(2, supplier.getAddress());
	            stmt.setString(3, supplier.getPhone());
	            stmt.setInt(4, supplier.getSuppliersid());
	            return stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }

	    @Override
	    public int delete(int supplierId) {
	        String sql = "DELETE FROM Suppliers WHERE supplier_id = ?";
	        try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, supplierId);
	            return stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }

	    @Override
	    public ArrayList<SuppliersModel> selectAll() {
	        ArrayList<SuppliersModel> suppliers = new ArrayList<>();
	        String sql = "SELECT supplier_id, name, address, phone FROM Suppliers";
	        try (Connection conn = JDBC.getconConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int supplierId = rs.getInt("supplier_id");
	                String name = rs.getString("name");
	                String address = rs.getString("address");
	                String phone = rs.getString("phone");
	                SuppliersModel supplier = new SuppliersModel(supplierId, name, address, phone);
	                suppliers.add(supplier);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return suppliers;
	    }

	    @Override
	    public SuppliersModel selectById(int supplierId) {
	        String sql = "SELECT supplier_id, name, address, phone FROM Suppliers WHERE supplier_id = ?";
	        try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, supplierId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    String name = rs.getString("name");
	                    String address = rs.getString("address");
	                    String phone = rs.getString("phone");
	                    return new SuppliersModel(supplierId, name, address, phone);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	

    public boolean isNamePhoneExist(String name, String phone) {
        String sql = "SELECT COUNT(*) AS count FROM Suppliers WHERE name = ? AND phone = ?";
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

    public ArrayList<Integer> getAllSupplierIDs() {
        ArrayList<Integer> supplierIDs = new ArrayList<>();
        String sql = "SELECT supplier_id FROM Suppliers";
        try (Connection conn = JDBC.getconConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                supplierIDs.add(rs.getInt("supplier_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierIDs;
    }

    public static int getID(String name, String phone) {
        String sql = "SELECT supplier_id FROM Suppliers WHERE name = ? AND phone = ?";
        Connection conn = JDBC.getconConnection();
        int supplierID = 0; // Khởi tạo supplierID với giá trị mặc định là 0

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                supplierID = resultSet.getInt("supplier_id");
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

        return supplierID;
    }
}
