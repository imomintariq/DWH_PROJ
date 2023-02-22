package ETL;


import DBHandler.DatabaseHandler;
import DataSourceEntities.Transactions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class StreamData {

    private static int current_index ;
    private static int last_processed_trans_id;
    public static List<Transactions> transactionsList;

    private int queue_size = 10;

    public Queue InputQueue = null;
    public final HashMap<String, HashMap> streamHashTableProductId = new HashMap();
    public final HashMap<String, HashMap> streamHashTableCustomerId = new HashMap<>();

    public void setUp(){
        last_processed_trans_id = -1;
        current_index = 0;
        InputQueue = DataQueue.getInputDataQueue();
    }

    public boolean loadQueue() throws SQLException, ClassNotFoundException {

        if (InputQueue.size() > queue_size) {
            return true;
        }
        Transactions transactions = new Transactions();
        if(transactionsList == null) {
            DatabaseHandler db_handler = new DatabaseHandler();
            transactionsList = db_handler.retrieveTransactions();
        }
        int i = current_index;
        processTransactionRecord(transactionsList.get(i));
        current_index++;
        if(current_index >= transactionsList.size()){
            return false;
        }
        else{
            return true;
        }

    }

    private void processTransactionRecord(Transactions transactions) {
        try {

            HashMap record = new HashMap();
            last_processed_trans_id = transactions.getId();

            record.put("transaction_id", last_processed_trans_id);
            record.put("product_id", transactions.getProductId());
            record.put("customer_id", transactions.getCustomerId());
            record.put("store_id", transactions.getStoreId());
            record.put("store_name", transactions.getStoreName());
            record.put("time_id", transactions.getTimeId());
            record.put("t_date", transactions.getTDate());
            record.put("quantity", transactions.getQuantity());

            InputQueue.offer(record);

            //Product Id instead of Trans id
            streamHashTableProductId.put(transactions.getProductId(), record);
            streamHashTableCustomerId.put(transactions.getCustomerId(), record);

            System.out.println("Transaction ID = "+ last_processed_trans_id + record.toString());
        } catch (Exception e) {
        }

    }
}
