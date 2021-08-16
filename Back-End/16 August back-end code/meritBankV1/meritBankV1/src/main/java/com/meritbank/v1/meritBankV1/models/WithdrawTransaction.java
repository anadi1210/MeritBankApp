package com.meritbank.v1.meritBankV1.models;

import javax.persistence.Entity;

@Entity
public class WithdrawTransaction extends Transaction{

	public WithdrawTransaction() {
		transactionType = "withdraw";
	}
	
	@Override
	public void processTransaction(Transaction t) {
		
	}
}
