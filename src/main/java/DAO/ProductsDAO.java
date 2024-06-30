package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.ProductsModel;
import JDBC.JDBC;

public class ProductsDAO implements DAO<ProductsModel> {

    @Override
    public int insert(ProductsModel product) {
        String sql = "INSERT INTO Products (product_id, name, description, price, quantity_available, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBC.getconConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getProductid());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setFloat(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.setInt(6, product.getCategoryid());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(ProductsModel product) {
        String sql = "UPDATE Products SET name = ?, description = ?, price = ?, quantity_available = ?, category_id = ? WHERE product_id = ?";
        try (Connection conn = JDBC.getconConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setFloat(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getCategoryid());
            stmt.setInt(6, product.getProductid());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int productid) {
        String sql = "DELETE FROM Products WHERE product_id = ?";
        try (Connection conn = JDBC.getconConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productid);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<ProductsModel> selectAll() {
        ArrayList<ProductsModel> products = new ArrayList<>();
        String sql = "SELECT product_id, name, description, price, quantity_available, category_id FROM Products";
        try (Connection conn = JDBC.getconConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int productid = rs.getInt("product_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity_available");
                int categoryid = rs.getInt("category_id");
                ProductsModel product = new ProductsModel(productid, name, description, price, quantity, categoryid);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public ProductsModel selectById(int productid) {
        String sql = "SELECT product_id, name, description, price, quantity_available, category_id FROM Products WHERE product_id = ?";
        try (Connection conn = JDBC.getconConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public List<Integer> getAllProductIDs() {
        List<Integer> productIDs = new ArrayList<>();
        String sql = "SELECT product_id FROM Products";
        try (Connection conn = JDBC.getconConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productIDs.add(rs.getInt("product_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productIDs;
    }
}
