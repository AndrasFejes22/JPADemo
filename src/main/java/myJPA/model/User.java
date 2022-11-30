package myJPA.model;


import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users", schema = "catalogs")
@SequenceGenerator(name = "userIdGenerator", sequenceName = "users_seq", initialValue = 1, allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")

    private long id;  // @Id: megjelöli, hogy melyik az az oszlop ami az elsödleges kulcsot jelöli

    //@Column(name ="username")//**VAGY így bejelöljük
    private String username;

    @Enumerated(EnumType.STRING)
    private UserStatus status;


    @Column(name = "created_at", precision = 28, length = 10)
    private ZonedDateTime createdAt;

    @Embedded
    public Address address;

    private transient boolean loggedIn; // nem egy perzisztens mező, nem is jelenik meg a lekérdezésben vagy:

    /*
    @Transient // a kulcsszó csak példányváltozóra rakható, az annotáció meg kb mindenhova
    private boolean isActive;
    */

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User() { //erre a JPA-nak van szüksége, szól is ha nincs: "ERROR: no default constructor", private: jelezzük, hogy a Hibernate-nak van

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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", address=" + address +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
