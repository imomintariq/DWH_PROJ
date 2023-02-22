package ETL;

import DBHandler.DatabaseHandler;
import DataSourceEntities.Customers;
import DataSourceEntities.Products;
import HQL_Queries.DataSource_HQL_Queries;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MasterData {


    private static int current_index ;
    private static int customer_table_size;
    private static int product_table_size;
    public static int total_master_size;
    public static List<Products> productsList;
    public static List<Customers> customersList;


    private int masterRecordCount = 0;
    public int masterPartitionSize = 10;
    public final HashMap<String, HashMap> masterBufferTable = new HashMap();


    public void setUp() throws SQLException, ClassNotFoundException {
        current_index = 0;
        DatabaseHandler db_handler = new DatabaseHandler();
        product_table_size = db_handler.retrieveProducts().size();
        customer_table_size = db_handler.retrieveCustomers().size();
        total_master_size = customer_table_size+product_table_size;
        System.out.println("total master size = "+total_master_size);
    }
    public void loadMasterPartition() throws SQLException, ClassNotFoundException {
        masterBufferTable.clear();

        if(current_index < customer_table_size) {
            loadCustomers();
        }
        if(masterBufferTable.size() >= masterPartitionSize){
            System.out.println("What?");
            return;
        }
        System.out.println("current index = "+current_index );
        if(current_index < (product_table_size + customer_table_size) && current_index >= customer_table_size){
            System.out.println("current index = "+current_index );
            loadProducts();
        }
        if(current_index >= total_master_size){
            current_index = 0;
        }
    }

    public void loadCustomers() throws SQLException, ClassNotFoundException {

        Customers customers = new Customers();
        if(customersList == null) {
            DatabaseHandler db_handler = new DatabaseHandler();
            customersList = db_handler.retrieveCustomers();
        }
        int i = current_index;
        for(; i < customersList.size(); i++) {
            processMasterRecordCustomers(customersList.get(i));
            current_index++;
            if (masterBufferTable.size() >= masterPartitionSize) {
                break;
            }
        }

    }

    private void processMasterRecordCustomers(Customers customers) {
        try {

            HashMap record = new HashMap();
            String pid = customers.getId();

            record.put("customer_id", customers.getId());
            record.put("customer_name", customers.getCustomerName());

            this.masterBufferTable.put(pid, record);
            System.out.println("PID = "+ pid + record.toString());
        } catch (Exception e) {
        }

    }
    public void loadProducts() throws SQLException, ClassNotFoundException {

        Products products = new Products();
        if(productsList == null){
            DatabaseHandler db_handler = new DatabaseHandler();
            productsList = db_handler.retrieveProducts();
        }

        int i = current_index-customer_table_size;
        for(; i < productsList.size();i++) {
            processMasterRecordProduct(productsList.get(i));
            current_index++;
            if (masterBufferTable.size() >= masterPartitionSize) {
                break;
            }
        }

    }

    private void processMasterRecordProduct(Products products) {
        try {

            HashMap record = new HashMap();
            String pid = products.getId();

            record.put("product_id", pid);
            record.put("product_name", products.getProductName());
            record.put("supplier_id",products.getSupplierId());
            record.put("supplier_name", products.getSupplierName());
            record.put("product_price", products.getPrice());

            this.masterBufferTable.put(pid, record);
            System.out.println("PID = "+ pid + record.toString());
        } catch (Exception e) {
        }

    }
}
