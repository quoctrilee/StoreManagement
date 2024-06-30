package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.CategoriesModel;
import JDBC.JDBC;

public class CategoriesDAO implements DAO<CategoriesModel> {

    @Override
    public int insert(CategoriesModel category) {
        String sql = "INSERT INTO Categories (category_id, name) VALUES (?, ?)";
        try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, category.getCategoryid());
            stmt.setString(2, category.getName());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(CategoriesModel category) {
        String sql = "UPDATE Categories SET name = ? WHERE category_id = ?";
        try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getCategoryid());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int categoryid) {
        String sql = "DELETE FROM Categories WHERE category_id = ?";
        try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryid);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<CategoriesModel> selectAll() {
        ArrayList<CategoriesModel> categories = new ArrayList<>();
        String sql = "SELECT category_id, name FROM Categories";
        try (Connection conn = JDBC.getconConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int categoryid = rs.getInt("category_id");
                String name = rs.getString("name");
                CategoriesModel category = new CategoriesModel(categoryid, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public CategoriesModel selectById(int categoryid) {
        String sql = "SELECT category_id, name FROM Categories WHERE category_id = ?";
        try (Connection conn = JDBC.getconConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    return new CategoriesModel(categoryid, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getAllCategoryIDs() {
        ArrayList<Integer> categoryIDs = new ArrayList<>();
        String sql = "SELECT category_id FROM Categories";
        try (Connection conn = JDBC.getconConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categoryIDs.add(rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryIDs;
    }
}
