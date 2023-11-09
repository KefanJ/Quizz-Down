package com.example.quizz_down;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/quizzdown";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
