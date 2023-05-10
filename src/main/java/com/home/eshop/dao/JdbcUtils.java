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

}
