package DWHEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "STORE_ID", nullable = false, length = 4)
    private String id;

    @Column(name = "STORE_NAME", length = 20)
    private String storeName;

    public Store(String store_id, String store_name) {
        this.setId(store_id);
        this.setStoreName(store_name);
    }

    public Store() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

}