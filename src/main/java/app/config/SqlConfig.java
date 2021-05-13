package app.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConfig {
    private static Connection connection;
    private static String URL = "jdbc:mysql://localhost:3306/deliverydb";
    private static String USER = "root";
    private static String PASSWORD = "password";

    private static Logger logger = LogManager.getLogger(SqlConfig.class);

    private SqlConfig() {
    }

    public static Connection getDataBaseConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                logger.info("Successfully connected to DB");
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
        return connection;
    }

    public static void closeDataBaseConnection() {
        try {
            if( connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Successfully closed connection with DB");
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }
}
