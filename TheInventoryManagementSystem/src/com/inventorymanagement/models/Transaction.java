package com.inventorymanagement.models;

import com.inventorymanagement.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
    private int id;
    private int productId;
    private String transactionType; // Either "SALE" or "PURCHASE"
    private int quantity;
    private Date transactionDate;

    // Constructors
    public Transaction() {
    }

    public Transaction(int id, int productId, String transactionType, int quantity, Date transactionDate) {
        this.id = id;
        this.productId = productId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }

    public Transaction(int productId, String transactionType, int quantity) {
        this.productId = productId;
        this.transactionType = transactionType;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }




    // Methods

    // Add a new transaction to the database.

    public boolean addTransaction() {
        String query = "INSERT INTO transaction (transaction_id, product_id, quantity, transaction_type, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            stmt.setString(2, transactionType);
            stmt.setInt(3, quantity);
           


            if (stmt.executeUpdate() > 0) {
                // Update product stock based on transaction type
                return updateProductStock(transactionType, productId, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    // Fetch a transaction by ID.

    public static Transaction getTransactionById(int transactionId) {
        String query = "SELECT * FROM Transaction WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Transaction(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("transaction_type"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("transaction_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    // Get all transactions from the database.

    public static List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM Transaction";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("transaction_type"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("transaction_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }


    // Delete a transaction by ID.

    public boolean deleteTransaction(int transactionId) {
        String query = "DELETE FROM Transaction WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transactionId);

            // Revert product stock update before deleting transaction
            Transaction transaction = getTransactionById(transactionId);
            if (transaction != null) {
                revertProductStock(transaction);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //Update product stock based on transaction type.
    private boolean updateProductStock(String transactionType, int productId, int quantity) {
        String query = transactionType.equals("SALE") ?
                "UPDATE Product SET quantity = quantity - ? WHERE id = ?" :
                "UPDATE Product SET quantity = quantity + ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    // Revert product stock update in case of a transaction deletion.

    private boolean revertProductStock(Transaction transaction) {
        String query = transaction.getTransactionType().equals("SALE") ?
                "UPDATE Product SET quantity = quantity + ? WHERE id = ?" :
                "UPDATE Product SET quantity = quantity - ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transaction.getQuantity());
            stmt.setInt(2, transaction.getProductId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", productId=" + productId +
                ", transactionType='" + transactionType + '\'' +
                ", quantity=" + quantity +
                ", transactionDate=" + transactionDate +
                '}';
    }
}