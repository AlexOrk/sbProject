package bank_api.dao;

import bank_api.entity.Card;

import java.util.List;

public interface CardDAO {

	public List<Card> findAll();

	public Card findById(int id);

    public void save(Card card);

}
