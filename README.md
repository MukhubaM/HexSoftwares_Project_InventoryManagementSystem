# HexSoftwares_Project_InventoryManagementSystem
Inventory Management System is a Java-based desktop application designed to help businesses efficiently manage their inventory and transactions. Built with a user-friendly graphical interface using Swing and robust database integration with MySQL, this system offers essential functionalities to streamline inventory management processes.


Features
Product Management:
Add, update, delete, and view product details, including name, quantity, and price.

Transaction Management:
Record, update, and view transaction details, including sales and purchases.

Search and Filter:
Search products and transactions using filters for quick access to information.

Database Integration:
Stores data securely in a MySQL database for persistence and reliability.

Responsive GUI:
Intuitive graphical interface built with Java Swing for ease of use.

Technologies Used
Programming Language: Java
Database: MySQL
GUI Framework: Swing
Database Connectivity: JDBC
Installation and Setup
Clone the Repository:

bash
"Copy code"
git clone https://github.com/yourusername/inventory-management-system.git
cd inventory-management-system
Database Setup:

Create a MySQL database named inventorydb.
Execute the SQL script inventorydb.sql (included in the repository) to create required tables.


Configure Database Connection:
Update the database connection settings in the DatabaseConnection class:

String url = "jdbc:mysql://localhost:3306/inventorydb";
String user = "yourusername";
String password = "yourpassword";
Run the Application:

Open the project in an IDE (e.g., IntelliJ IDEA or Eclipse).
Build and run the application.
Project Structure
models: Contains classes for handling business logic (e.g., Product, Transaction).
views: Includes GUI components (e.g., ProductPanel, TransactionPanel).
controllers: Manages the interaction between views and models.
utils: Utility classes for database connection and other shared functionality.
Future Enhancements
Add reporting and analytics for inventory trends.
Implement user authentication and role-based access control.
Provide support for exporting data to CSV or PDF formats.
Integrate with barcode scanning hardware for easier product management.
