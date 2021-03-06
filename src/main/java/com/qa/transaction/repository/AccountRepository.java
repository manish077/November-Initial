package com.qa.transaction.repository;

public interface AccountRepository {

	String getAllAccounts();

	String createAccount(String account);

	String updateAccount(Long id, String accountToUpdate);

	String deleteAccount(Long id);

}
