package com.meritbank.v1.meritBankV1.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BankAccount {

	//static int counter = 1;
	//protected long bankAccountId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	protected double balance;
	//protected double interestRate;
	protected LocalDateTime openedOn;
	
	//protected int ownerId;

		
	public BankAccount() {
		//this.accountNumber = counter++;
		//this.openedOn = new Date();
	}
	
	BankAccount(double balance, double interestRate){
		this.balance = balance;
		//this.interestRate = interestRate;
		
	}

	BankAccount(double balance, double interestRate, 
					Date accountOpenedOn) {
		this.balance = balance;
		//this.interestRate = interestRate;
		//this.openedOn = accountOpenedOn;
		//this.accountNumber = counter++;
	}
	BankAccount(long accountNumber, double balance, 
			double interestRate, Date accountOpenedOn) {
		//this.accountNumber = accountNumber;
		this.balance = balance;
		//this.interestRate = interestRate;
		//this.openedOn = accountOpenedOn;
	}

	/*
	 * public long getBankAccountId() { return bankAccountId; }
	 * 
	 * public void setBankAccountId(long bankAccountId) { this.bankAccountId =
	 * bankAccountId; }
	 */
	
	public double getBalance() {
		return balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	/*
	 * public double getInterestRate() { return interestRate; }
	 * 
	 * public void setInterestRate(double interestRate) { this.interestRate =
	 * interestRate; }
	 */

	public LocalDateTime getOpenedOn() {
		return openedOn;
	}

	public void setOpenedOn(LocalDateTime openedOn) {
		this.openedOn = openedOn;
	}
	
	public boolean withdraw(double amount) {
		
		if(amount != 0 && amount <= this.balance) {
			this.balance -= amount;
			return true;
		}
		
		return false;
	}
	public boolean deposit (double amount) {
		
		if(amount > 0.0)
		{
			this.balance += amount;
			return true;
		}
		return false;
	}
	
	
}
