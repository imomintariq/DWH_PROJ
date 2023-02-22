package DBHandler;


import DataSourceEntities.Customers;
import DataSourceEntities.Products;
import DataSourceEntities.Transactions;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    public List<Customers> retrieveCustomers() throws ClassNotFoundException, SQLException {
        List<Customers> customersList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","tiger");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");
        while(rs.next()){
            Customers customers = new Customers();
            customers.setId(rs.getString("CUSTOMER_ID"));
            customers.setCustomerName(rs.getString("CUSTOMER_NAME"));
            customersList.add(customers);
        }
        con.close();
        return customersList;
    }
    public List<Products> retrieveProducts() throws ClassNotFoundException, SQLException {
        List<Products> productsList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","tiger");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
        while(rs.next()){
            Products products = new Products();
            products.setId(rs.getString("PRODUCT_ID"));
            products.setProductName(rs.getString("PRODUCT_NAME"));
            products.setSupplierId(rs.getString("SUPPLIER_ID"));
            products.setSupplierName(rs.getString("SUPPLIER_NAME"));
            products.setPrice(rs.getDouble("PRICE"));
            productsList.add(products);
        }
        con.close();
        return productsList;
    }
    public List<Transactions> retrieveTransactions() throws ClassNotFoundException, SQLException {
        List<Transactions> transactionsList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","tiger");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Transactions");
        while(rs.next()){
            Transactions transactions = new Transactions();
            transactions.setId(rs.getInt("TRANSACTION_ID"));
            transactions.setProductId(rs.getString("PRODUCT_ID"));
            transactions.setCustomerId(rs.getString("CUSTOMER_ID"));
            transactions.setStoreId(rs.getString("STORE_ID"));
            transactions.setStoreName(rs.getString("STORE_NAME"));
            transactions.setTimeId(rs.getString("TIME_ID"));
            transactions.setTDate(LocalDate.parse(rs.getString("T_DATE")));
            transactions.setQuantity(rs.getDouble("QUANTITY"));
            transactionsList.add(transactions);
        }
        con.close();
        return transactionsList;
    }
}
