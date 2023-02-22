package DWHEntities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name = "TRANSACTION_ID", nullable = false)
    private Double id;

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

    public Transaction(String product_id, String customer_id, String store_id, String store_name, String time_id, LocalDate t_date, double quantity) {

        this.setProductId(product_id);
        this.setCustomerId(customer_id);
        this.setStoreId(store_id);
        this.setStoreName(store_name);
        this.setTimeId(time_id);
        this.setTDate(t_date);
        this.setQuantity(quantity);
    }

    public Transaction() {

    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
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

}