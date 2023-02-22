package DWHEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID", nullable = false, length = 4)
    private String id;

    @Column(name = "CUSTOMER_NAME", nullable = false, length = 30)
    private String customerName;

    public Customer(String customer_id, String customer_name) {
        this.setId(customer_id);
        this.setCustomerName(customer_name);
    }

    public Customer() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}