package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static final String DB_PROPERTIES_PATH = "db.properties";

    private static Connection connection = null;

    private DB() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException exception) {
                throw new DbException(exception.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream dbProperties = new FileInputStream(DB_PROPERTIES_PATH)) {
            Properties properties = new Properties();
            properties.load(dbProperties);
            return properties;
        } catch (IOException exception) {
            throw new DbException(exception.getMessage());
        }
    }
}
