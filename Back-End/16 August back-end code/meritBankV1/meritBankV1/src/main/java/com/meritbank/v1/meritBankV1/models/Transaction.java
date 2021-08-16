package com.meritbank.v1.meritBankV1.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private double amount;
	private LocalDate txnDate ;
	private long sourceAccountNumber;
	private long targetAccountNumber;
	protected String transactionType;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private BankAccount sourceAccount;
	@ManyToOne(cascade = CascadeType.MERGE)
	private BankAccount targetAccount;
	
	
	public Transaction() {
		//txnDate = LocalDate.now();
	}
	
	public Transaction(double amount, LocalDate txnDate, long sourceAccountId, long targetAccountId,
			BankAccount sourceAccount, BankAccount targetAccount) {
		super();
		this.amount = amount;
		this.txnDate = txnDate;
		this.sourceAccountNumber = sourceAccountId;
		this.targetAccountNumber = targetAccountId;
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(LocalDate txnDate) {
		this.txnDate = txnDate;
	}

	public BankAccount getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public BankAccount getTargetAccount() {
		return targetAccount;
	}
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}
	
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void processTransaction(Transaction t) {
		
	}
	
	public long getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(long sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public long getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(long targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}
	

}
