package com.home.eshop.dao;

import com.home.eshop.model.Bike;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoJDBC implements Dao {

    static {
        JdbcUtils.initDriver("org.postgresql.Driver");
    }

    private Connection getConnectionShop() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/shop";
        final String username = "postgres";
        final String password = "1q2w3e";
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public int create(Bike bike) {
        String sqlBrand = "INSERT INTO bikes_brand (brand) SELECT ? " +
                "WHERE NOT EXISTS(SELECT brand FROM bikes_brand WHERE brand= ? )";
        String sqlType = "INSERT INTO bikes_type (type) SELECT ? " +
                "WHERE NOT EXISTS(SELECT type FROM bikes_type WHERE type = ?)";
        String sqlColor = "INSERT INTO bikes_color (color) SELECT ? " +
                "WHERE NOT EXISTS(SELECT color FROM bikes_color WHERE color= ? )";
        String sqlBike = "INSERT INTO bikes (model, price, quantity, weight, brand_id, type_id, color_id) " +
                "VALUES (?,?,?,?," +
                "(SELECT id FROM bikes_brand WHERE brand = ?)," +
                "(SELECT id FROM bikes_type WHERE type = ?)," +
                "(SELECT id FROM bikes_color WHERE color = ?)" +
                ")RETURNING id ";


        try (Connection connection = getConnectionShop();
             PreparedStatement preparedStatementSqlBrand = connection.prepareStatement(sqlBrand);
             PreparedStatement preparedStatementSqlType = connection.prepareStatement(sqlType);
             PreparedStatement preparedStatementSqlColor = connection.prepareStatement(sqlColor);
             PreparedStatement preparedStatementSqlBike = connection.prepareStatement(sqlBike, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatementSqlBrand.setString(1, bike.getBrand());
            preparedStatementSqlBrand.setString(2, bike.getBrand());
            preparedStatementSqlBrand.executeUpdate();


            preparedStatementSqlType.setString(1, bike.getType());
            preparedStatementSqlType.setString(2, bike.getType());
            preparedStatementSqlType.executeUpdate();


            preparedStatementSqlColor.setString(1, bike.getColor());
            preparedStatementSqlColor.setString(2, bike.getColor());
            preparedStatementSqlColor.executeUpdate();


            preparedStatementSqlBike.setString(1, bike.getModel());
            preparedStatementSqlBike.setBigDecimal(2, bike.getPrice());
            preparedStatementSqlBike.setInt(3, bike.getQuantity());
            preparedStatementSqlBike.setFloat(4, bike.getWeight());
            preparedStatementSqlBike.setString(5, bike.getBrand());
            preparedStatementSqlBike.setString(6, bike.getType());
            preparedStatementSqlBike.setString(7, bike.getColor());

            int result = 0;
            if (preparedStatementSqlBike.executeUpdate() > 0) {
                ResultSet generatedKeys = preparedStatementSqlBike.getGeneratedKeys();
                if (generatedKeys.next()) {
                    result = generatedKeys.getInt(1);
                }
                generatedKeys.close();
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to Create bike", e);
        }
    }

    @Override
    public Bike read(int id) {
        String sqlBike = "SELECT bikes.id,brand,model,type,price,quantity,color,weight FROM bikes " +
                "LEFT JOIN bikes_brand ON bikes.brand_id = bikes_brand.id " +
                "LEFT JOIN bikes_type ON bikes.type_id = bikes_type.id " +
                "LEFT JOIN bikes_color ON bikes.color_id = bikes_color.id " +
                "WHERE bikes.id = ?";

        try (Connection connection = getConnectionShop();
             PreparedStatement preparedStatementSqlBike = connection.prepareStatement(sqlBike)) {

            preparedStatementSqlBike.setInt(1, id);
            ResultSet resultSet = preparedStatementSqlBike.executeQuery();

            Bike result = null;
            if (resultSet.next()) {
                int bikesId = resultSet.getInt("id");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String type = resultSet.getString("type");
                BigDecimal price = resultSet.getBigDecimal("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                float weight = resultSet.getFloat("weight");

                result = new Bike(bikesId, brand, model, type, price, quantity, color, weight);
            }
            resultSet.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to Read bike", e);
        }
    }


    @Override
    public int update(Bike bike) {
        String sqlBrand = "INSERT INTO bikes_brand (brand) SELECT ? " +
                "WHERE NOT EXISTS(SELECT brand FROM bikes_brand WHERE brand= ? )";
        String sqlType = "INSERT INTO bikes_type (type) SELECT ? " +
                "WHERE NOT EXISTS(SELECT type FROM bikes_type WHERE type = ?)";
        String sqlColor = "INSERT INTO bikes_color (color) SELECT ? " +
                "WHERE NOT EXISTS(SELECT color FROM bikes_color WHERE color= ? )";
        String sqlBike = "UPDATE bikes SET model= ? , price= ? , quantity= ? , weight= ? ," +
                "brand_id=(SELECT id FROM bikes_brand WHERE brand= ? ), " +
                "type_id=(SELECT id FROM bikes_type WHERE type= ? ), " +
                "color_id=(SELECT id FROM bikes_color WHERE color= ? ) " +
                "WHERE bikes.id = ? ";

        try (Connection connection = getConnectionShop();
             PreparedStatement preparedStatementSqlBrand = connection.prepareStatement(sqlBrand);
             PreparedStatement preparedStatementSqlType = connection.prepareStatement(sqlType);
             PreparedStatement preparedStatementSqlColor = connection.prepareStatement(sqlColor);
             PreparedStatement preparedStatementSqlBike = connection.prepareStatement(sqlBike)) {

            preparedStatementSqlBrand.setString(1, bike.getBrand());
            preparedStatementSqlBrand.setString(2, bike.getBrand());
            preparedStatementSqlBrand.executeUpdate();

            preparedStatementSqlType.setString(1, bike.getType());
            preparedStatementSqlType.setString(2, bike.getType());
            preparedStatementSqlType.executeUpdate();

            preparedStatementSqlColor.setString(1, bike.getColor());
            preparedStatementSqlColor.setString(2, bike.getColor());
            preparedStatementSqlColor.executeUpdate();

            preparedStatementSqlBike.setString(1, bike.getModel());
            preparedStatementSqlBike.setBigDecimal(2, bike.getPrice());
            preparedStatementSqlBike.setInt(3, bike.getQuantity());
            preparedStatementSqlBike.setFloat(4, bike.getWeight());
            preparedStatementSqlBike.setString(5, bike.getBrand());
            preparedStatementSqlBike.setString(6, bike.getType());
            preparedStatementSqlBike.setString(7, bike.getColor());
            preparedStatementSqlBike.setInt(8, bike.getId());
            int result = preparedStatementSqlBike.executeUpdate();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to Update bike", e);
        }
    }

    @Override
    public int delete(int id) {
        String sqlBike = "DELETE FROM bikes WHERE id = ?";
        String sqlColorBrandType = "DELETE FROM bikes_color WHERE NOT EXISTS " +
                "(SELECT * FROM bikes WHERE bikes_color.id = bikes.color_id);" +
                "DELETE FROM bikes_brand WHERE NOT EXISTS " +
                "(SELECT * FROM bikes WHERE bikes_brand.id = bikes.brand_id);" +
                "DELETE FROM bikes_type WHERE NOT EXISTS " +
                "(SELECT * FROM bikes WHERE bikes_type.id = bikes.type_id);";

        try (Connection connection = getConnectionShop();
             PreparedStatement preparedStatementSqlBike = connection.prepareStatement(sqlBike);
             PreparedStatement preparedStatementSqlColorBrandType = connection.prepareStatement(sqlColorBrandType)) {

            preparedStatementSqlBike.setInt(1, id);
            int result = preparedStatementSqlBike.executeUpdate();

            preparedStatementSqlColorBrandType.executeUpdate();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to Delete bike", e);
        }
    }

    @Override
    public List<Bike> findAll() {
        String sqlBikes = "SELECT bikes.id,brand,model,type,price,quantity,color,weight FROM bikes " +
                "JOIN bikes_brand ON bikes.brand_id = bikes_brand.id " +
                "JOIN bikes_type ON bikes.type_id = bikes_type.id " +
                "JOIN bikes_color ON bikes.color_id = bikes_color.id ";

        try (Connection connection = getConnectionShop();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlBikes)) {

            List<Bike> result = new ArrayList<>();
            while (resultSet.next()) {
                int bikesId = resultSet.getInt("id");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String type = resultSet.getString("type");
                BigDecimal price = resultSet.getBigDecimal("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                float weight = resultSet.getFloat("weight");

                result.add(new Bike(bikesId, brand, model, type, price, quantity, color, weight));
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to FindAll bikes", e);
        }
    }
}
