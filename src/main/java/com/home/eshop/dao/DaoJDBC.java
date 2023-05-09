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
    public int create(Bike bike) throws SQLException {
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

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = getConnectionShop();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            preparedStatement = connection.prepareStatement(sqlBrand);
            preparedStatement.setString(1, bike.getBrand());
            preparedStatement.setString(2, bike.getBrand());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlType);
            preparedStatement.setString(1, bike.getType());
            preparedStatement.setString(2, bike.getType());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlColor);
            preparedStatement.setString(1, bike.getColor());
            preparedStatement.setString(2, bike.getColor());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlBike, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bike.getModel());
            preparedStatement.setBigDecimal(2, bike.getPrice());
            preparedStatement.setInt(3, bike.getQuantity());
            preparedStatement.setFloat(4, bike.getWeight());
            preparedStatement.setString(5, bike.getBrand());
            preparedStatement.setString(6, bike.getType());
            preparedStatement.setString(7, bike.getColor());
            int result = 0;
            if (preparedStatement.executeUpdate() > 0) {
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    result = generatedKeys.getInt(1);
                }
            }

            connection.commit();
            return result;

        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw e;
        } finally {
            JdbcUtils.closeQuietly(generatedKeys);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }

    @Override
    public Bike read(int id) throws SQLException {
        String sqlBike = "SELECT bikes.id,brand,model,type,price,quantity,color,weight FROM bikes " +
                "LEFT JOIN bikes_brand ON bikes.brand_id = bikes_brand.id " +
                "LEFT JOIN bikes_type ON bikes.type_id = bikes_type.id " +
                "LEFT JOIN bikes_color ON bikes.color_id = bikes_color.id " +
                "WHERE bikes.id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnectionShop();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            preparedStatement = connection.prepareStatement(sqlBike);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
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

            connection.commit();
            return result;

        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw e;
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }


    @Override
    public int update(Bike bike) throws SQLException {
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

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnectionShop();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            preparedStatement = connection.prepareStatement(sqlBrand);
            preparedStatement.setString(1, bike.getBrand());
            preparedStatement.setString(2, bike.getBrand());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlType);
            preparedStatement.setString(1, bike.getType());
            preparedStatement.setString(2, bike.getType());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlColor);
            preparedStatement.setString(1, bike.getColor());
            preparedStatement.setString(2, bike.getColor());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlBike);
            preparedStatement.setString(1, bike.getModel());
            preparedStatement.setBigDecimal(2, bike.getPrice());
            preparedStatement.setInt(3, bike.getQuantity());
            preparedStatement.setFloat(4, bike.getWeight());
            preparedStatement.setString(5, bike.getBrand());
            preparedStatement.setString(6, bike.getType());
            preparedStatement.setString(7, bike.getColor());
            preparedStatement.setInt(8, bike.getId());
            int result = preparedStatement.executeUpdate();

            connection.commit();
            return result;

        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw e;
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sqlBike = "DELETE FROM bikes WHERE id = ?";
        String sqlColorBrandType = "DELETE FROM bikes_color WHERE NOT EXISTS " +
                "(SELECT * FROM bikes WHERE bikes_color.id = bikes.color_id);" +
                "DELETE FROM bikes_brand WHERE NOT EXISTS " +
                "(SELECT * FROM bikes WHERE bikes_brand.id = bikes.brand_id);" +
                "DELETE FROM bikes_type WHERE NOT EXISTS " +
                "(SELECT * FROM bikes WHERE bikes_type.id = bikes.type_id);";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnectionShop();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            preparedStatement = connection.prepareStatement(sqlBike);
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlColorBrandType);
            preparedStatement.executeUpdate();

            connection.commit();
            return result;

        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw e;
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }

    @Override
    public List<Bike> findAll() throws SQLException {
        String sqlBikes = "SELECT bikes.id,brand,model,type,price,quantity,color,weight FROM bikes " +
                "JOIN bikes_brand ON bikes.brand_id = bikes_brand.id " +
                "JOIN bikes_type ON bikes.type_id = bikes_type.id " +
                "JOIN bikes_color ON bikes.color_id = bikes_color.id ";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnectionShop();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sqlBikes);

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
            connection.commit();
            return result;

        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(connection);
            throw e;
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(statement);
            JdbcUtils.closeQuietly(connection);
        }
    }
}
