package bank_api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client",
            cascade = CascadeType.ALL)
    private List<Account> accounts;


// define constructors

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
// define getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    // define toString

    @Override
    public String toString() {
        return "{ \"id\": " + id + ", \"name\":\" " + name + "\", " +
                "\"accounts\":" +
                accounts.toString() +
                " }";
    }

}










