package com.qa.persistence.repository.transtactions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import com.qa.account.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class Transaction {
	
	@PersistenceContext(unitName="primary")
	private EntityManager manager;
	
	public String createAnAccount(String account) {
		Account anAccount = JSONUtil.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account added\"}";
	}
	
	public List<Account> findAllAccount(){
		TypedQuery<Account> query = manager.createQuery("SELECT m From Account m ORDER m.firstName DESC",Account.class);
		return query.getResultList();
	}
	
	public Account findAccount(Long id) {
		return manager.find(Account.class, id);
	}
	
	@Transactional(REQUIRED)
	public Account create(Account account) {
		manager.persist(account);
		return account;
	}
	
	@Transactional(REQUIRED)
	public Account update(Account account1) {
		manager.merge(account1);
		return account1;
	}
	
	@Transactional(REQUIRED)
	public Account delete(Account account) {
		manager.remove(account);
		return account;
	}
	

	
}
