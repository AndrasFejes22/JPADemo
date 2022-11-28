package myJPA.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "catalogs")
public class User {

    @Id
    private long id;

    //@Column(name ="username")//**VAGY így bejelöljük
    private String username;

    private User() { //erre a JPA-nak van szüksége, szól is ha nincs: "ERROR: no default constructor", private: jelezzük, hogy a Hibernate-nak van

    }

    public User(long id, String username) {
        super();
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
