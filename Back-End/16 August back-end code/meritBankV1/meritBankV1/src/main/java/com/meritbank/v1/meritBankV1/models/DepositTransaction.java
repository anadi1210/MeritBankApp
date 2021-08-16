package com.meritbank.v1.meritBankV1.models;

import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction{

	public DepositTransaction() {
		transactionType = "deposit";
	}
	
	@Override
	public void processTransaction(Transaction t) {
		
	}
}
