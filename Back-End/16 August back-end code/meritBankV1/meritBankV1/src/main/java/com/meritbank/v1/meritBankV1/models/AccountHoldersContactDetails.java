package com.meritbank.v1.meritBankV1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class AccountHoldersContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountHolderContactDetailsId;
	
	private String phoneNumber;
	
	@Email
	private String email;
	
	private String address;
	
	@Column(name = "account_holder_id")
	private int accountHolderId;
	
	//private AccountHolder accountHolder;
	
	
	public AccountHoldersContactDetails() {
		
	}
	
	public long getAccountHolderContactDetailsId() {
		return accountHolderContactDetailsId;
	}
	public void setAccountHolderContactDetailsId(long accountHolderContactDetailsId) {
		this.accountHolderContactDetailsId = accountHolderContactDetailsId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAccountHolderId() {
		return accountHolderId;
	}
	public void setAccountHolderId(int accountHolderId) {
		this.accountHolderId = accountHolderId;
	}

	
}
