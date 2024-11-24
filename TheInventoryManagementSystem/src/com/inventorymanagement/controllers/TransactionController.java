package com.inventorymanagement.controllers;

import com.inventorymanagement.models.Transaction;

import java.util.List;

public class TransactionController {


    public List<Transaction> getAllTransactions() {
        return Transaction.getAllTransactions();
    }


    public boolean addTransaction(int productId, String transactionType, int quantity) {
        Transaction transaction = new Transaction(productId, transactionType, quantity);
        return transaction.addTransaction();
    }


    public Transaction getTransactionById(int id) {
        return Transaction.getTransactionById(id);
    }


    public boolean deleteTransaction(int id) {
        return new Transaction().deleteTransaction(id);
    }
}