package com.inventorymanagement.database;

import com.inventorymanagement.database.DatabaseConnectionManager;

import java.sql.Connection;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        Connection connection = DatabaseConnectionManager.getConnection();
        if (connection != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed.");
        }
    }
}