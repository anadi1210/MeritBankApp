package com.meritbank.v1.meritBankV1.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CDAccount extends BankAccount{

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO) private long cdAccountId;
	 */
	
	@ManyToOne(cascade = CascadeType.ALL)
	CDOffering cdOffering;
 	
	@Column(name = "account_holder_id")
	private int accountHolderId;
	
	public CDAccount() {
		
	}
	
	public CDAccount(CDOffering offering, double balance){
		this.cdOffering = offering;
		super.balance = balance;
	}
 	
	/*
	 * public long getCdAccountId() { return cdAccountId; }
	 * 
	 * public void setCdAccountId(long cdAccountId) { this.cdAccountId =
	 * cdAccountId; }
	 */
	public int getAccountHolderId() {
		return accountHolderId;
	}

	public void setAccountHolderId(int accountHolderId) {
		this.accountHolderId = accountHolderId;
	}
	
	public CDOffering getCdOffering() {
		return cdOffering;
	}
	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}
	
}
