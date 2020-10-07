package bank_api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="account")
public class Account implements Serializable {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="number")
	private long number;

	@Column(name = "amount")
	private BigDecimal amount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account",
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Card> cards;

	// define constructors

	public Account() {}

	public Account(long number, BigDecimal amount) {
		this.number = number;
		this.amount = amount;
	}

	// define getter/setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	// define toString

	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", amount=" + amount + "]";
	}

}