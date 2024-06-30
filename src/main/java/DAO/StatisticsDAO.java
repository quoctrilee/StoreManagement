package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import JDBC.JDBC;

public class StatisticsDAO {
    public Map<String, Double> getDailySales() {
        Map<String, Double> dailySales = new HashMap<>();
        String sql = "SELECT CONVERT(date, order_date) AS date, SUM(total_amount) AS total_sales \r\n"
        		+ "FROM orders \r\n"
        		+ "GROUP BY CONVERT(date, order_date);";
        
        try (Connection conn = JDBC.getconConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            
            while (rs.next()) {
                String date = rs.getString("date");
                double totalSales = rs.getDouble("total_sales");
                dailySales.put(date, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dailySales;
    }
}
