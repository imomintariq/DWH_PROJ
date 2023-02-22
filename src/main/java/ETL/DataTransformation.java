package ETL;

import DBHandler.DataWareHouseHandler;
import HQL_Queries.DWH_HQL_Queries;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class DataTransformation {

    DWH_HQL_Queries dwh_query;
    DataWareHouseHandler dataWareHouseHandler;
    public void setUp(){
        dwh_query = new DWH_HQL_Queries();
        dataWareHouseHandler = new DataWareHouseHandler();
    }
    public void transformData(HashMap<String, HashMap> data) throws SQLException, ClassNotFoundException {

        //System.out.println("DATE ID = "+ data.get("time_id"));

        dataWareHouseHandler.insertDate(String.valueOf(data.get("time_id")), LocalDate.parse(String.valueOf(data.get("t_date"))));
        System.out.println("Date Inserted");
        dataWareHouseHandler.insertStore(String.valueOf(data.get("store_id")),String.valueOf(data.get("store_name")));
        dataWareHouseHandler.insertProduct(String.valueOf(data.get("product_id")), String.valueOf(data.get("product_name")),Double.parseDouble(String.valueOf(data.get("product_price"))));
        dataWareHouseHandler.insertCustomer(String.valueOf(data.get("customer_id")),String.valueOf(data.get("customer_name")));
        dataWareHouseHandler.insertSupplier(String.valueOf(data.get("supplier_id")), String.valueOf(data.get("supplier_name")));
        dataWareHouseHandler.insertTrans(Double.parseDouble(String.valueOf(data.get("transaction_id"))),String.valueOf(data.get("product_id")),String.valueOf(data.get("customer_id")),String.valueOf(data.get("store_id")),String.valueOf(data.get("store_name")),String.valueOf(data.get("time_id")), LocalDate.parse(String.valueOf(data.get("t_date"))),Double.parseDouble(String.valueOf(data.get("quantity"))));
        dataWareHouseHandler.insertSale(Double.parseDouble(String.valueOf(data.get("transaction_id"))), String.valueOf(data.get("product_id")), String.valueOf(data.get("supplier_id")), String.valueOf(data.get("customer_id")), String.valueOf(data.get("store_id")), String.valueOf(data.get("time_id")),Double.parseDouble(String.valueOf(data.get("quantity")))*Double.parseDouble(String.valueOf(data.get("product_price"))));



    }
}
