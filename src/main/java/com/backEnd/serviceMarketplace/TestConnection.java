package com.backEnd.serviceMarketplace;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TestConnection {

        public static void main(String[] args) {
            String url = "jdbc:postgresql://localhost:5432/servicemarketdb";
            String username = "myuser";
            String password = "mypassword@321";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection successful!");
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection failed!");
            }
        }

}
