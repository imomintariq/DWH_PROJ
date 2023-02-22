
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class MainScreen implements Initializable {

    private ArrayList<String> selected_transactions;

    @FXML
    private ListView<String> list_transactions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selected_transactions = new ArrayList<>();
        loadTransactions();
    }

    private void loadTransactions() {
        //Transaction tns = new Transaction();
        //ArrayList<String> k = tns.retrieveTransactionsAsString();
        //list_transactions.getItems().addAll(k);
    }
}