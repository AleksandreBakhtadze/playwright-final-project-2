package ge.tbc.testautomation.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DatabaseSteps {

    private Properties properties;

    public DatabaseSteps() {
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection connectToDatabase() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        return  DriverManager.getConnection(url, user, password);
    }

    // ქვეყნების გზავნილების საკომისიოების წამოსაღებად
    public List<Map<String, Object>> getCommissionsForCountry(String countryName) throws SQLException {
        List<Map<String, Object>> commissions = new ArrayList<>();
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT c.system_name, c.commission_details " +
                             "FROM commissions c " +
                             "JOIN countries co ON c.country_id = co.id " +
                             "WHERE co.name = ? " +
                             "ORDER BY c.order_index")) {
            stmt.setString(1, countryName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> commission = new HashMap<>();
                commission.put("system_name", rs.getString("system_name"));
                commission.put("commission_details", rs.getString("commission_details"));
                commissions.add(commission);
            }
        }
        return commissions;
    }

    // ყვეყნების გზავნილების კომპანიების რაოდენობის წამოსაღებად
    public Integer getExpectedCardCount(String countryName) throws SQLException {
        Integer count = null;
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT expected_card_count FROM countries WHERE name = ?")) {
            stmt.setString(1, countryName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("expected_card_count");
            }
        }
        return count;
    }

}