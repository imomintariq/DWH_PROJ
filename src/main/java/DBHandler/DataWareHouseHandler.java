package DBHandler;

import DWHEntities.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DataWareHouseHandler {



    public void insertSupplier(String supplier_id, String supplier_name) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwh","root","tiger");

        String sql = " insert ignore into Supplier (SUPPLIER_ID , SUPPLIER_NAME)"
                + " values (?, ?)";


        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString  (1, supplier_id);
        preparedStmt.setString  (2, supplier_name);

        preparedStmt.execute();
        con.close();

    }

    public void insertCustomer(String customer_id, String customer_name) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwh","root","tiger");

        String sql = " insert ignore into Customer (CUSTOMER_ID , CUSTOMER_NAME)"
                + " values (?, ?)";


        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString  (1, customer_id);
        preparedStmt.setString  (2, customer_name);

        preparedStmt.execute();
        con.close();

    }

    public void insertStore(String store_id, String store_name) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwh","root","tiger");

        String sql = " insert ignore into Store (STORE_ID , STORE_NAME)"
                + " values (?, ?)";


        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString  (1, store_id);
        preparedStmt.setString  (2, store_name);

        preparedStmt.execute();
        con.close();


    }

    public void insertProduct(String product_id, String product_name, double product_price) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwh","root","tiger");

        String sql = " insert ignore into Product (PRODUCT_ID, PRODUCT_NAME, PRICE)"
                + " values (?, ?, ?)";

        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString  (1, product_id);
        preparedStmt.setString  (2, product_name);
        preparedStmt.setDouble(3, product_price);

        preparedStmt.execute();
        con.close();


    }

    public void insertDate(String date_id, LocalDate t_date) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwh","root","tiger");

        Date date = new Date();
        date.setId(date_id);
        date.setDd(t_date.getDayOfMonth());
        date.setMm(t_date.getMonthValue());
        int month = t_date.getMonthValue();
        int quater = 0;
        if (month <= 3) {
            quater = 1;
        } else if (month <= 6) {
            quater = 2;
        } else if (month <= 9) {
            quater = 3;
        } else if (month <= 12) {
            quater = 4;
        }

        date.setQtr(quater);
        date.setYyyy(t_date.getYear());

        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = t_date.get(woy);

        date.setWeekOfYear(weekNumber);
        date.setWeekday(t_date.getDayOfWeek().toString());
        String sql = " insert ignore into Date (DATE_ID, DD, MM, QTR,YYYY, WEEK_OF_YEAR, WEEKDAY)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString  (1, date.getId());
        preparedStmt.setInt     (2, date.getDd());
        preparedStmt.setInt     (3, date.getMm());
        preparedStmt.setInt     (4, date.getQtr());
        preparedStmt.setInt     (5, date.getYyyy());
        preparedStmt.setInt     (6, date.getWeekOfYear());
        preparedStmt.setString  (7, date.getWeekday());

        preparedStmt.execute();
        con.close();

    }


    public void insertTrans(double transaction_id, String product_id, String customer_id, String store_id, String store_name, String time_id, LocalDate t_date, double quantity) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwh","root","tiger");

        String sql = " insert ignore into transaction (TRANSACTION_ID, PRODUCT_ID, CUSTOMER_ID, STORE_ID, STORE_NAME,TIME_ID, T_DATE , QUANTITY )"
                + " values (?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setDouble  (1, transaction_id);
        preparedStmt.setString  (2, product_id);
        preparedStmt.setString  (3, customer_id);
        preparedStmt.setString  (4, store_id);
        preparedStmt.setString  (5, store_name);
        preparedStmt.setString  (6, time_id);
        preparedStmt.setString  (7, String.valueOf(t_date));
        preparedStmt.setDouble  (8, quantity);

        preparedStmt.execute();
        con.close();
    }

    public void insertSale(double transaction_id, String product_id, String supplier_id, String customer_id, String store_id, String time_id, double v) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwh","root","tiger");


        String sql = " insert ignore into Sales (TRANSACTION_ID, PRODUCT_ID, SUPPLIER_ID, CUSTOMER_ID, STORE_ID,DATE_ID, TOTAL_SALE )"
                + " values (?,?,?,?,?,?,?)";

        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setDouble  (1, transaction_id);
        preparedStmt.setString  (2, product_id);
        preparedStmt.setString  (3, supplier_id);
        preparedStmt.setString  (4, customer_id);
        preparedStmt.setString  (5, store_id);
        preparedStmt.setString  (6, time_id);
        preparedStmt.setDouble  (7, v);

        preparedStmt.execute();
        con.close();
    }
}
