package DWHEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "SUPPLIER_ID", nullable = false, length = 5)
    private String id;

    @Column(name = "SUPPLIER_NAME", length = 30)
    private String supplierName;

    public Supplier(String supplier_id, String supplier_name) {
        this.setId(supplier_id);
        this.setSupplierName(supplier_name);
    }

    public Supplier() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

}