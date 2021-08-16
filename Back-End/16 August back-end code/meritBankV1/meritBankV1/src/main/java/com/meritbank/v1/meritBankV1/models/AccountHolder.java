package com.meritbank.v1.meritBankV1.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class AccountHolder {

static int counter = 1;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_holder_id")
	protected long accountHolderId;
	
	@NotBlank(message = "first name is required")
	protected String firstName;
	
	protected String middleName;
	
	//@NotBlank(message = "last name is required")
	protected String lastName;
	
	@NotBlank(message = "ssn is required")
	protected String SSN;
	
	@NotBlank(message = "Username is required")
	protected String username;
	
	@NotBlank(message = "Password is required")
	protected String password;
	
	@Email
	@NotBlank(message = "email is required")
	protected String email;
	
	
	protected String mobile;
	
	protected String address;
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL) //@JoinColumn(name =
	 * "account_holder_id", referencedColumnName = "account_holder_id") protected
	 * AccountHoldersContactDetails accountHoldersContactDetails;
	 */
	
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "account_holder_id", referencedColumnName = "account_holder_id")
	protected CheckingAccount checkingAccount;

	@OneToOne(cascade = CascadeType.ALL)
	protected SavingsAccount savingsAccounts;
	
	@OneToMany(cascade = CascadeType.ALL)
	protected List<CDAccount> cdAccountsList;
	
	@OneToMany(cascade = CascadeType.ALL)
	protected List<DBACheckingAccount> dbaCheckingAccountList;
	
	public List<DBACheckingAccount> getDbaCheckingAccountList() {
		return dbaCheckingAccountList;
	}
	public void setDbaCheckingAccountList(List<DBACheckingAccount> dbaCheckingAccountList) {
		this.dbaCheckingAccountList = dbaCheckingAccountList;
	}
	
	@OneToOne(mappedBy = "accountHolder" ,cascade = CascadeType.ALL)
	protected User user;

	protected int numberOfCheckingAccounts;

	protected double checkingBalance;
	protected int numberOfSavingsAccounts;
	protected double savingsBalance;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	protected int numberOfCDAccounts;
	protected double cdBalance;
	protected double combinedBalance;
	
	public AccountHolder() {
			//checkingAccount = new CheckingAccount();
			//savingsAccounts= new SavingsAccount();
			cdAccountsList= new ArrayList<CDAccount>();
			//user = new User(this.username, this.password);
			//accountHoldersContactDetails = new AccountHoldersContactDetails();
		}
	AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.SSN = ssn;
	}

	/*
	 * public int getNumberOfCheckingAccounts() { return checkingAccountList.size();
	 * }
	 */

	
	  public double getCheckingBalance() {
		  if(checkingAccount != null) {
			  	double checkingBalance = checkingAccount.getBalance();
		  		return checkingBalance; 
		  }
		  return 0;
	  }
	 
	/*
	 * public int getNumberOfSavingsAccounts() { return savingsAccountsList.size();
	 * }
	 */
	
	  public double getSavingsBalance() {
		 if(savingsAccounts != null) {
			 double savingsBalance = savingsAccounts.getBalance();
			 return savingsBalance; 
		 }
		 return 0;
	  }
	 

	public int getNumberOfCDAccounts() {
		return cdAccountsList.size();
	}

	public double getCdBalance() {
		double cdBalance = 0.0;
		if(cdAccountsList != null) {
			for(int i = 0 ; i < cdAccountsList.size() ; i++) {
				cdBalance += cdAccountsList.get(i).getBalance();
			}
		}
		return cdBalance;
	}

	
	  public double getCombinedBalance() { 
		  double totalBalance = getCdBalance() +  getCheckingBalance() + getSavingsBalance();
		  return totalBalance;
	  }
	 

	
	
	/*
	 * public int getAccountId() { return accountHolderId; }
	 * 
	 * public void setAccountId(int accountId) { this.accountHolderId = accountId; }
	 */
	  
	public String getFirstName() { return firstName; }
	  
	public void setFirstName(String firstName) { this.firstName = firstName; }
	  
	public String getMiddleName() { return middleName; }
	  
	public void setMiddleName(String middleName) { this.middleName = middleName; }
	  
	public String getLastName() { return lastName; }
	  
	public void setLastName(String lastName) { this.lastName = lastName; }
	  
	public String getSSN() { return SSN; }
	  
	public void setSSN(String sSN) { SSN = sSN; }
	
	
	public List<CDAccount> getCdAccountsList() {
		return cdAccountsList;
	}
	public void setCdAccountsList(List<CDAccount> cdAccountsList) {
		this.cdAccountsList = cdAccountsList;
	}
	
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}
	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	public SavingsAccount getSavingsAccounts() {
		return savingsAccounts;
	}
	public void setSavingsAccounts(SavingsAccount savingsAccounts) {
		this.savingsAccounts = savingsAccounts;
	}
	public long getAccountHolderId() {
		return accountHolderId;
	}
	public void setAccountHolderId(int accountHolderId) {
		this.accountHolderId = accountHolderId;
	}
	
	public void setCheckingBalance(double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}
	public void setNumberOfSavingsAccounts(int numberOfSavingsAccounts) {
		this.numberOfSavingsAccounts = numberOfSavingsAccounts;
	}
	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}
	public void setNumberOfCDAccounts(int numberOfCDAccounts) {
		this.numberOfCDAccounts = numberOfCDAccounts;
	}
	public void setCdBalance(double cdBalance) {
		this.cdBalance = cdBalance;
	}
	public void setCombinedBalance(double combinedBalance) {
		this.combinedBalance = combinedBalance;
	}
	public String getUsername() {
	return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/*
	 * public AccountHoldersContactDetails getAccountHoldersContactDetails() {
	 * return accountHoldersContactDetails; } public void
	 * setAccountHoldersContactDetails(AccountHoldersContactDetails
	 * accountHoldersContactDetails) { this.accountHoldersContactDetails =
	 * accountHoldersContactDetails; }
	 */

	/*
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		checkingAccount.setOwnerId(this.getAccountId());
		checkingAccountsList.add(checkingAccount);
		return checkingAccount;
	}
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
		savingsAccount.setOwnerId(this.getAccountId());
		savingsAccountsList.add(savingsAccount);
		return savingsAccount;
	}
	public CDAccount addCDAccount(CDAccount cdAccount) {
		cdAccount.setOwnerId(this.getAccountId());
		cdAccountsList.add(cdAccount);
		return cdAccount;
	}
	*/
}
