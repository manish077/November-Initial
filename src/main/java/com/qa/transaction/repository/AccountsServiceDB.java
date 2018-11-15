package com.qa.transaction.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.account.Account;
import com.qa.transaction.repository.AccountRepository;
import com.qa.util.JSONUtil;


@Transactional(SUPPORTS)
@Default
public class AccountsServiceDB implements AccountRepository{
	
	@PersistenceContext(unitName="primary")
	private EntityManager manager;
	
	@Inject private JSONUtil util;
	
	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}
	
	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account anAccount = JSONUtil.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account successfully added\"}";
	}
	
	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Account accountInDB = findAccount(id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	private Account findAccount(Long id) {
		return manager.find(Account.class, id);
	}
	
	@Override
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		Account updatedAccount = JSONUtil.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		if (accountToUpdate != null) {
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}
	
	public void setManager(EntityManager em) {
		this.manager =em;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}
	
	
}
