package database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

        private static DatabaseConnection INSTANCE = null;
        private static Connection connection = null;

        private final String url = "jdbc:postgresql://localhost:5432/BatiCuisine";
        private final String user = "postgres";
        private final String password = "123";

        private DatabaseConnection() {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static DatabaseConnection getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new DatabaseConnection();
            }
            return INSTANCE;
        }

        public Connection getConnection() {
            return connection;
        }
    }


