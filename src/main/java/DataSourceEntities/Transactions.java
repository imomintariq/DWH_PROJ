package DataSourceEntities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name = "TRANSACTION_ID", nullable = false)
    private int id;

    @Column(name = "PRODUCT_ID", nullable = false, length = 6)
    private String productId;

    @Column(name = "CUSTOMER_ID", nullable = false, length = 4)
    private String customerId;

    @Column(name = "STORE_ID", nullable = false, length = 4)
    private String storeId;

    @Column(name = "STORE_NAME", nullable = false, length = 20)
    private String storeName;

    @Column(name = "TIME_ID", nullable = false, length = 8)
    private String timeId;

    @Column(name = "T_DATE", nullable = false)
    private LocalDate tDate;

    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public LocalDate getTDate() {
        return tDate;
    }

    public void setTDate(LocalDate tDate) {
        this.tDate = tDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public List<Transactions> retrieveTransactions() {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Transactions.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        List<Transactions> transactions_list = session.createQuery("FROM Transactions").getResultList();
        return transactions_list;
    }
}