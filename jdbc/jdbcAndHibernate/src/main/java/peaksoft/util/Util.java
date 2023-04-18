package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    url,
                    username,
                    password);
            System.out.println("Successfully connected to database...");
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    }



