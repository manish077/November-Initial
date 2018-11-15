package com.qa.business;

public interface AccountService {

	String getAllAccounts();

	String addAccount(String account);

	String updateAccount(Long id, String account);

	String deleteAccount(Long id);

}