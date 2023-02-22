package DataSourceEntities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @Column(name = "PRODUCT_ID", nullable = false, length = 6)
    private String id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 30)
    private String productName;

    @Column(name = "SUPPLIER_ID", nullable = false, length = 5)
    private String supplierId;

    @Column(name = "SUPPLIER_NAME", nullable = false, length = 30)
    private String supplierName;

    @Column(name = "PRICE")
    private Double price;

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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Products> retrieveProducts() {

        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Products.class);
        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        List<Products> productsList = session.createQuery("FROM Products").getResultList();
        return productsList;
    }

}