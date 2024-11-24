package com.inventorymanagement.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0, 102, 204)); // Ocean blue background

        // Title
        JLabel title = new JLabel("Inventory Management System", JLabel.CENTER);
        title.setForeground(new Color(245, 245, 220)); // Beige color
        title.setFont(new Font("Arial", Font.BOLD, 24));

        // Buttons
        JButton productButton = new JButton("Manage Products");
        JButton transactionButton = new JButton("Manage Transactions");

        productButton.addActionListener((ActionEvent e) -> {
            new ProductPanel().setVisible(true);
        });

        transactionButton.addActionListener((ActionEvent e) -> {
            new TransactionPanel().setVisible(true);
        });

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(productButton);
        buttonPanel.add(transactionButton);

        // Add components to main panel
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set content pane
        setContentPane(mainPanel);
    }

}