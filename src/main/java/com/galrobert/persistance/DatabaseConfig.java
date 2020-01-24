package com.galrobert.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    public static Connection getConnection() throws IOException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (InputStream dbproperties = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            if (dbproperties != null) {
                properties.load(dbproperties);
            }

            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")

            );
        }
    }
}
