package com.qa.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table
public class Account {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column (length=100)
	private String firstName;
	@Column (length = 100)
	private String secondName;
	@Column
	private int accountNumber;
	
	public Account(String firstName, String secondName, int accountNumber)
	{
		super();
		//this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.accountNumber = accountNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", accountNumber="
				+ accountNumber + "]";
	}
	
	
	
	
}
