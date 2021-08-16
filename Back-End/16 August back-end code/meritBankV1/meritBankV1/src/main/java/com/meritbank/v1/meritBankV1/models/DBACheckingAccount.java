package com.meritbank.v1.meritBankV1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DBACheckingAccount extends BankAccount{

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO) private long id;
	 */
	private int accountHolderId;
	private double interestRate;
	
	public DBACheckingAccount() {
		
	}
	
	public DBACheckingAccount(long id, int accountHolderId) {
		super();
		//this.id = id;
		this.accountHolderId = accountHolderId;
	}
	
	/*
	 * public long getId() {return id;} public void setId(long id) {this.id = id;}
	 */
	public int getAccountHolderId() {return accountHolderId;}
	public void setAccountHolderId(int accountHolderId) {this.accountHolderId = accountHolderId;}
	public double getInterestRate() {return interestRate;}
	public void setInterestRate(double interestRate) {this.interestRate = interestRate;}
}
