package com.inventorymanagement.views;

import com.inventorymanagement.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProductPanel extends JFrame {
    private JTable productTable;
    private DefaultTableModel tableModel;

    public ProductPanel() {
        setTitle("Manage Products");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Quantity"}, 0);
        productTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(productTable);

        // Buttons
        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        // Add action listeners
        addButton.addActionListener(this::addProduct);
        updateButton.addActionListener(this::updateProduct);
        deleteButton.addActionListener(this::deleteProduct);
        refreshButton.addActionListener(e -> loadProducts());

        // Add components to frame
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load products on startup
        loadProducts();
    }

    private void loadProducts() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Product> products = Product.getAllProducts();
        for (Product product : products) {
            tableModel.addRow(new Object[]{product.getId(), product.getName(), product.getPrice(), product.getQuantity()});
        }
    }

    private void addProduct(ActionEvent e) {
        String name = JOptionPane.showInputDialog(this, "Enter Product Name:");
        double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Product Price:"));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Product Quantity:"));

        Product product = new Product(name, price, quantity);
        if (product.addProduct()) {
            JOptionPane.showMessageDialog(this, "Product added successfully.");
            loadProducts();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add product.");
        }
    }

    private void updateProduct(ActionEvent e) {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a product to update.");
            return;
        }

        int productId = (int) tableModel.getValueAt(selectedRow, 0);
        String name = JOptionPane.showInputDialog(this, "Enter New Product Name:", tableModel.getValueAt(selectedRow, 1));
        double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter New Product Price:", tableModel.getValueAt(selectedRow, 2)));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter New Product Quantity:", tableModel.getValueAt(selectedRow, 3)));

        Product product = new Product(productId, name, price, quantity);
        if (product.updateProduct()) {
            JOptionPane.showMessageDialog(this, "Product updated successfully.");
            loadProducts();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update product.");
        }
    }

    private void deleteProduct(ActionEvent e) {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a product to delete.");
            return;
        }

        int productId = (int) tableModel.getValueAt(selectedRow, 0);
        if (new Product().deleteProduct(productId)) {
            JOptionPane.showMessageDialog(this, "Product deleted successfully.");
            loadProducts();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete product.");
        }
    }
}