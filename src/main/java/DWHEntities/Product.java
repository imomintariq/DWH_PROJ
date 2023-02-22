package DWHEntities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "PRODUCT_ID", nullable = false, length = 6)
    private String id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 30)
    private String productName;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "product")
    private Set<Sale> sales = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

}