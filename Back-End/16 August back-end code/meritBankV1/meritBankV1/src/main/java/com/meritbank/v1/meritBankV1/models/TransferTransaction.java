package com.meritbank.v1.meritBankV1.models;

import javax.persistence.Entity;

@Entity
public class TransferTransaction extends Transaction{

	public TransferTransaction() {
		transactionType = "transfer";
	}
	
	@Override
	public void processTransaction(Transaction t) {
		
	}
}
