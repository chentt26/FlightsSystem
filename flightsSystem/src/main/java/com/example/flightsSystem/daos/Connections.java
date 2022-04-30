package com.example.flightsSystem.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connections {
    private static Connection connection = null;
    private static Statement statement = null;

    public Connections() {

    }

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/FlightsData", "postgres", "1234");
            System.out.println("Done!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static Statement getStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;

    }

    public static void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
