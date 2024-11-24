package com.inventorymanagement;

import com.inventorymanagement.database.DatabaseConnectionManager;
import com.inventorymanagement.views.MainWindow;

import javax.swing.*;
import java.sql.Connection;

public class InventoryManagementApp {


    public static void main(String[] args) {
        // Initialize the application
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize the database connection
                Connection connection = DatabaseConnectionManager.getConnection();
                if (connection != null) {
                    System.out.println("Database connected successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }

                // Launch the main window
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}