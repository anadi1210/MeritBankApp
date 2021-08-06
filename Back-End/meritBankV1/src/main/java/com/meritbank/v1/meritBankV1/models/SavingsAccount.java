package com.meritbank.v1.meritBankV1.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SavingsAccount extends BankAccount{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long savingsAccountId;
	
	private double savingsInterestRate;
	
	@Column(name = "account_holder_id")
	private int accountHolderId;
	
	public SavingsAccount() {
		super();
		savingsInterestRate = 0.01;
	}
	
	/*
	 * SavingsAccount(double openingBalance){
	 * super(openingBalance,savingsInterestRate); }
	 */

	/*
	SavingsAccount(double balance, double interestRate){
		super(balance,interestRate);
	}
	
	 * SavingsAccount(double balance, double interestRate, Date accountOpenedOn) {
	 * super(balance,interestRate,accountOpenedOn); } SavingsAccount(long
	 * accountNumber, double balance, double interestRate, Date accountOpenedOn) {
	 * super(accountNumber,balance,interestRate,accountOpenedOn); }
	 */
	public double getSavingsInterestRate() {
		return savingsInterestRate;
	}
	public void setSavingsInterestRate(double savingsInterestRate) {
		this.savingsInterestRate = savingsInterestRate;
	}
	public long getSavingsAccountId() {
		return savingsAccountId;
	}
	public void setSavingsAccountId(long savingsAccountId) {
		this.savingsAccountId = savingsAccountId;
	}
	public int getAccountHolderId() {
		return accountHolderId;
	}
	public void setAccountHolderId(int accountHolderId) {
		this.accountHolderId = accountHolderId;
	}
}
