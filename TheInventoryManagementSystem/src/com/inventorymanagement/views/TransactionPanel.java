package com.inventorymanagement.views;

import com.inventorymanagement.models.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TransactionPanel extends JFrame {
    private JTable transactionTable;
    private DefaultTableModel tableModel;

    public TransactionPanel() {
        setTitle("Manage Transactions");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Product ID", "Type", "Quantity", "Date"}, 0);
        transactionTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(transactionTable);

        // Buttons
        JButton addButton = new JButton("Add Transaction");
        JButton refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);

        // Add action listeners
        addButton.addActionListener(this::addTransaction);
        refreshButton.addActionListener(e -> loadTransactions());

        // Add components to frame
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load transactions on startup
        loadTransactions();
    }

    private void loadTransactions() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Transaction> transactions = Transaction.getAllTransactions();
        for (Transaction transaction : transactions) {
            tableModel.addRow(new Object[]{
                    transaction.getId(),
                    transaction.getProductId(),
                    transaction.getTransactionType(),
                    transaction.getQuantity(),
                    transaction.getTransactionDate()
            });
        }
    }

    private void addTransaction(ActionEvent e) {
        int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Product ID:"));
        String type = JOptionPane.showInputDialog(this, "Enter Transaction Type (SALE or PURCHASE):");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Quantity:"));

        Transaction transaction = new Transaction(productId, type.toUpperCase(), quantity);
        if (transaction.addTransaction()) {
            JOptionPane.showMessageDialog(this, "Transaction added successfully.");
            loadTransactions();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add transaction.");
        }
    }
}