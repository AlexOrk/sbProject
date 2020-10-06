package bank_api.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="client")
public class Client implements Serializable {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;

	@OneToOne(mappedBy = "client",
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Account account;
	
		
	// define constructors
	
	public Client() {}

	public Client(String name) {
		this.name = name;
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


	// define toString

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}
		
}










