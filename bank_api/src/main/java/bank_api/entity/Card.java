package bank_api.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="card")
public class Card implements Serializable {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="number")
	private long number;

	@Column(name="expdate")
	private String expDate;

	@Column(name="cvv")
	private int cvv;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;

	// define constructors

	public Card() {}

	public Card(long number, String expDate, int cvv, Account account) {
		this.number = number;
		this.expDate = expDate;
		this.cvv = cvv;
		this.account = account;
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

	public String getExpDate() {
		return expDate;
	}

	public void setexpDate(String expDate) {
		this.expDate = expDate;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	// define toString

	@Override
	public String toString() {
		return "{ \"id\": " + id + ", \"number\": " + number +
				", \"expDate\": " + expDate + ", \"cvv\": " + cvv + "}";
	}

}
