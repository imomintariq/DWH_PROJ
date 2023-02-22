package HQL_Queries;



import DataSourceEntities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class DataSource_HQL_Queries {


    //Transactions Table
    public List<Transactions> queryTrans(Double id){

        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Transactions.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Transaction.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Transaction T WHERE T.id = :trans_id";
        Query query = session.createQuery(hql);
        query.setParameter("trans_id",id);
        List<Transactions> results = query.list();
        return results;
    }
    public void insertTrans(String product_id, String customer_id, String store_id, String store_name, String time_id, LocalDate tDate, double quantity){

        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Transactions.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Transaction.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Transactions transaction = new Transactions();

        transaction.setProductId(product_id);
        transaction.setCustomerId(customer_id);
        transaction.setStoreId(store_id);
        transaction.setStoreName(store_name);
        transaction.setTimeId(time_id);
        transaction.setTDate(tDate);
        transaction.setQuantity(quantity);

        session.save(transaction);
        trans.commit();
    }

    //Products Table
    public List<Products> queryProducts(String id){
        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Products.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Product.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Product P WHERE P.id = :product_id";
        Query query = session.createQuery(hql);
        query.setParameter("product_id",id);
        List<Products> results = query.list();
        return results;
    }
    public void insertProducts(String product_id, String product_name, String supplier_id, String supplier_name, Double price){
        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Products.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Product.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Products product = new Products();

        product.setId(product_id);
        product.setProductName(product_name);
        product.setSupplierId(supplier_id);
        product.setSupplierName(supplier_name);
        product.setPrice(price);

        session.save(product);
        trans.commit();
    }

    //Customer Table
    public List<Customers> queryCustomer(String id){
        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Customers.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Customer.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Customer C WHERE C.id = :customer_id";
        Query query = session.createQuery(hql);
        query.setParameter("customer_id",id);
        List<Customers> results = query.list();
        return results;
    }
    public void insertCustomer(String customer_id, String customer_name){
        Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Customers.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Customer.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Customers customer = new Customers();

        customer.setId(customer_id);
        customer.setCustomerName(customer_name);

        session.save(customer);
        trans.commit();
    }
}
