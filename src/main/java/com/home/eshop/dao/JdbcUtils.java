package com.home.eshop.dao;

import java.sql.*;

public final class JdbcUtils {
    private static boolean initialized;

    private JdbcUtils() {
    }

    public static synchronized void initDriver(String driverClass) {
        if (!initialized) {
            try {
                Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Can't initialized driver" + driverClass);
            }
            initialized = true;
        }
    }

    public static void rollbackQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to Connection Rollback", e);
            }
        }
    }

    public static void closeQuietly(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close ResultSet", e);
            }
        }
    }

    public static void closeQuietly(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close PreparedStatement", e);
            }
        }
    }

    public static void closeQuietly(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close Statement", e);
            }
        }
    }

    public static void closeQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close Connection", e);
            }
        }
    }

}
