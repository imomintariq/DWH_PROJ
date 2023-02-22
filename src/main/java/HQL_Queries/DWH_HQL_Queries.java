package HQL_Queries;

import DWHEntities.*;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

//import static jdk.internal.org.jline.utils.Colors.C;

public class DWH_HQL_Queries {

    //Transaction Table
    public List<Transaction> queryTrans(Double id){
        Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Transaction.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Transaction.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Transaction T WHERE T.id = :trans_id";
        Query query = session.createQuery(hql);
        query.setParameter("trans_id",id);
        List<Transaction> results = query.list();
        return results;
    }
    public void insertTrans(String product_id, String customer_id, String store_id, String store_name, String time_id, LocalDate tDate, double quantity){

        try {
            Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Transaction.class);
            //Configuration con = new Configuration();
            //con.configure().addAnnotatedClass(Transaction.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            Transaction transaction = new Transaction();

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
        catch(Exception e){

        }
    }
    //Sales Table
    public List<Sale> querySale(int id){

        Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Sale.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Sale.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Sale S WHERE S.id = :sale_id";
        Query query = session.createQuery(hql);
        query.setParameter("sale_id",id);
        List<Sale> results = query.list();
        return results;
    }
    public void insertSale(Transaction transaction, Product product, Supplier supplier, Customer customer, Store store, Date date, double total_sale){
        try {
            Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Sale.class);
            //Configuration con = new Configuration();
            //con.configure().addAnnotatedClass(Sale.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            Sale sale = new Sale();

            sale.setTransaction(transaction);
            sale.setProduct(product);
            sale.setSupplier(supplier);
            sale.setCustomer(customer);
            sale.setStore(store);
            sale.setDate(date);
            sale.setTotalSale(total_sale);

            session.save(sale);
            trans.commit();
        }catch(Exception e){

        }
    }

    //Supplier Table
    public List<Supplier> querySupplier(String id){
        Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Supplier.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Supplier.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Supplier S WHERE S.id = :supplier_id";
        Query query = session.createQuery(hql);
        query.setParameter("supplier_id",id);
        List<Supplier> results = query.list();
        return results;
    }
    public void insertSupplier(String supplier_id, String supplier_name){

        try {
            Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Supplier.class);
            //Configuration con = new Configuration();
            //con.configure().addAnnotatedClass(Supplier.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            Supplier supplier = new Supplier();

            supplier.setId(supplier_id);
            supplier.setSupplierName(supplier_name);

            session.save(supplier);
            trans.commit();
        }catch (Exception e){

        }
    }

    //Customer Table
    public List<Customer> queryCustomer(String id){
        Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Customer.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Customer.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Customer C WHERE C.id = :customer_id";
        Query query = session.createQuery(hql);
        query.setParameter("customer_id",id);
        List<Customer> results = query.list();
        return results;
    }
    public void insertCustomer(String customer_id, String customer_name){
        try {
            Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Customer.class);
            //Configuration con = new Configuration();
            //con.configure().addAnnotatedClass(Customer.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            Customer customer = new Customer();

            customer.setId(customer_id);
            customer.setCustomerName(customer_name);

            session.save(customer);
            trans.commit();
        }
        catch(Exception e){

        }
    }

    //Store Table
    public List<Store> queryStore(String id){
        Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Store.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Store.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Store S WHERE S.id = :store_id";
        Query query = session.createQuery(hql);
        query.setParameter("store_id",id);
        List<Store> results = query.list();
        return results;
    }
    public void insertStore(String store_id, String store_name){
        try {
            Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Store.class);
            //Configuration con = new Configuration();
            //con.configure().addAnnotatedClass(Store.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            Store store = new Store();

            store.setId(store_id);
            store.setStoreName(store_name);
            session.save(store);
            trans.commit();
        }
        catch(Exception e){

        }
    }

    //Product Table
    public List<Product> queryProduct(String id){
        Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Product.class);
        //Configuration con = new Configuration();
        //con.configure().addAnnotatedClass(Product.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Product P WHERE P.id = :product_id";
        Query query = session.createQuery(hql);
        query.setParameter("product_id",id);
        List<Product> results = query.list();
        return results;
    }
    public void insertProduct(String product_id, String product_name){
        try {
            Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Product.class);
            //Configuration con = new Configuration();
            //con.configure().addAnnotatedClass(Product.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            Product product = new Product();

            product.setId(product_id);
            product.setProductName(product_name);
            session.save(product);
            trans.commit();
        }
        catch (Exception e){

        }
    }

    //Date
    public List<Date> queryDate(String id){

        Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Date.class);
        //con.configure("/hibernate_dwh.cfg.xml");
        //con.configure().addAnnotatedClass(Date.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        String hql = "FROM Date D WHERE D.id = :date_id";
        Query query = session.createQuery(hql);
        query.setParameter("date_id",id);
        List<Date> results = query.list();
        return results;
    }


    public void insertDate(String date_id, LocalDate t_date){

        try {
            Configuration con = new Configuration().configure("/hibernate_dwh.cfg.xml").addAnnotatedClass(Date.class);
            //con.configure().addAnnotatedClass(Date.class);

            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
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

            session.saveOrUpdate(date);
            trans.commit();
        }
        catch (Exception r){
            System.out.println(r.getCause());
            System.out.println("Date id = " + date_id +" Date = "+ t_date);
        }
    }
}
