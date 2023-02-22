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
@Table(name = "customers")
public class Customers {
    @Id
    @Column(name = "CUSTOMER_ID", nullable = false, length = 4)
    private String id;

    @Column(name = "CUSTOMER_NAME", nullable = false, length = 30)
    private String customerName;

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

    public List<Customers> retrieveCustomers() {

        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Customers.class);
        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        List<Customers> customersList = session.createQuery("FROM Customers").getResultList();
        return customersList;
    }
}