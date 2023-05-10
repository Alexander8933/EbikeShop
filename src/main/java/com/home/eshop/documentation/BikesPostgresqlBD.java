package com.home.eshop.documentation;

public class BikesPostgresqlBD {

    String sql = "CREATE TABLE bikes (" +
            "id SERIAL NOT NULL PRIMARY KEY," +
            "model VARCHAR(50) NOT NULL," +
            "price NUMERIC(7,2) NOT NULL," +
            "quantity INT," +
            "weight NUMERIC(5,2) NOT NULL" +
            ");" +

            "CREATE TABLE bikes_brand (" +
            "id SERIAL NOT NULL PRIMARY KEY," +
            "brand VARCHAR(50) NOT NULL);" +

            "CREATE TABLE bikes_type (" +
            "id SERIAL NOT NULL PRIMARY KEY," +
            "type VARCHAR(50) NOT NULL);" +

            "CREATE TABLE bikes_color (" +
            "id SERIAL NOT NULL PRIMARY KEY," +
            "color VARCHAR(30) NOT NULL);" +

            "ALTER TABLE bikes ADD brand_id INT REFERENCES bikes_brand (id);" +
            "ALTER TABLE bikes ADD type_id INT REFERENCES bikes_type (id);" +
            "ALTER TABLE bikes ADD color_id INT REFERENCES bikes_color (id);";

}
