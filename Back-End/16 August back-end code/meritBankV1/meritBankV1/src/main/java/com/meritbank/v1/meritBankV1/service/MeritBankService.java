package com.meritbank.v1.meritBankV1.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritbank.v1.meritBankV1.Exceptions.AccountNotCreatedException;
import com.meritbank.v1.meritBankV1.models.AccountHolder;
import com.meritbank.v1.meritBankV1.models.BankAccount;
import com.meritbank.v1.meritBankV1.models.CDAccount;
import com.meritbank.v1.meritBankV1.models.CDOffering;
import com.meritbank.v1.meritBankV1.models.CheckingAccount;
import com.meritbank.v1.meritBankV1.models.DBACheckingAccount;
import com.meritbank.v1.meritBankV1.models.DepositTransaction;
import com.meritbank.v1.meritBankV1.models.SavingsAccount;
import com.meritbank.v1.meritBankV1.models.Transaction;
import com.meritbank.v1.meritBankV1.models.User;
import com.meritbank.v1.meritBankV1.models.WithdrawTransaction;
import com.meritbank.v1.meritBankV1.repos.AccountHolderRepository;
import com.meritbank.v1.meritBankV1.repos.CDAccountRepository;
import com.meritbank.v1.meritBankV1.repos.CDOfferingRepository;
import com.meritbank.v1.meritBankV1.repos.CheckingAccountRepository;
import com.meritbank.v1.meritBankV1.repos.DBACheckingAccountRepository;
import com.meritbank.v1.meritBankV1.repos.SavingsAccountRepository;
import com.meritbank.v1.meritBankV1.repos.TransactionRepository;
import com.meritbank.v1.meritBankV1.models.User;

@Service
public class MeritBankService {

	@Autowired
	CheckingAccountRepository chAccountRepository;
	@Autowired
	SavingsAccountRepository saAccountRepository;
	@Autowired
	CDAccountRepository cdAccountRepository;
	@Autowired
	AccountHolderRepository accHolderRepository;
	@Autowired
	CDOfferingRepository cdOfferingRepository;
	@Autowired
	DBACheckingAccountRepository dbaCheckAccRepository;
	@Autowired
	TransactionRepository transactionRepository;
	
	List<BankAccount> bankAccountList = new ArrayList<>();
	private static final double checkingInterestRate = 0.0001;
	private static final double savingInterestRate = 0.01;
	private static final double dbaCheckingInterestRate = 0.01;
	private static final int MAX_COMBINED_BALANCE = 250000;
	
	public CheckingAccount addCheckingAccount(long accountHolderId, double balance) {
		
		CheckingAccount chAccount = new CheckingAccount();
		chAccount.setBalance(balance);
		chAccount.setCheckingInterestRate(checkingInterestRate);
		chAccount.setAccountHolderId((int) accountHolderId);
		chAccount.setOpenedOn(LocalDateTime.now());
		accHolderRepository.findById(accountHolderId).setCheckingAccount(chAccount);
		bankAccountList.add(chAccount);
		return chAccountRepository.save(chAccount);
	}
	
	public CheckingAccount getCheckingAccount(long id) {
		
		return accHolderRepository.getById(id).getCheckingAccount();
	}
	
	public SavingsAccount addSavingsAccount(long accountHolderId, double balance) {
		
		SavingsAccount saAccount = new SavingsAccount();
		saAccount.setBalance(balance);
		saAccount.setSavingsInterestRate(savingInterestRate);
		saAccount.setAccountHolderId((int) accountHolderId);
		
		saAccount.setOpenedOn(LocalDateTime.now());
		accHolderRepository.findById(accountHolderId).setSavingsAccounts(saAccount);
		bankAccountList.add(saAccount);
		return saAccountRepository.save(saAccount);
	}
	
	public SavingsAccount getSavingsAccount(long id) {
		
		return accHolderRepository.getById(id).getSavingsAccounts();
	}
	
	public CDAccount addCDAccount(long accountHolderId, double balance, int cdOfferingId) {
		
		CDOffering offering =  cdOfferingRepository.findById(cdOfferingId).get();
		
		CDAccount cdAccount = new CDAccount(offering, balance);
		cdAccount.setOpenedOn(LocalDateTime.now());
		cdAccount.setAccountHolderId((int) accountHolderId);
		accHolderRepository.findById(accountHolderId).getCdAccountsList().add(cdAccount);
		bankAccountList.add(cdAccount);
		return cdAccountRepository.save(cdAccount);
	}	
	
	public List<CDAccount> getCDAccount(long id) {
		
		return cdAccountRepository.findAll();
	}
	
	public List<CDAccount> getCDAccountForAccountHolder(long accountHolderId) {
		
		return accHolderRepository.findById(accountHolderId).getCdAccountsList();
	}
	
	public DBACheckingAccount addDBACheckingAccount (int accountHolderId, double balance) throws AccountNotCreatedException {
		
		int noOfDBCheckAccount = accHolderRepository.findById(accountHolderId).getDbaCheckingAccountList().size();
		if(noOfDBCheckAccount < 4) {
			DBACheckingAccount dbCheckAcc = new DBACheckingAccount();
			dbCheckAcc.setBalance(balance);
			dbCheckAcc.setInterestRate(dbaCheckingInterestRate);
			dbCheckAcc.setAccountHolderId(accountHolderId);
			dbCheckAcc.setOpenedOn(LocalDateTime.now());
			accHolderRepository.findById(accountHolderId).getDbaCheckingAccountList().add(dbCheckAcc);
			bankAccountList.add(dbCheckAcc);
			return dbaCheckAccRepository.save(dbCheckAcc);
		}
		
		else {
			throw new AccountNotCreatedException("Max number of account reached, can have only 3 accounts");
		}
	}
	
	public boolean processTransaction(long id , Transaction t) {
		long  srcAccountNum = t.getSourceAccountNumber();
		long  tarAccountNum = t.getTargetAccountNumber();
		
		double amount = t.getAmount();
		
		BankAccount srcBankAccount = getBankAccountFromList(srcAccountNum);
		
		
		if(srcAccountNum == tarAccountNum) {
			
			if(amount > 0) {
				// Deposit
				 Transaction tr = new DepositTransaction();
				  tr.setSourceAccount(srcBankAccount);
				  tr.setTargetAccount(srcBankAccount);
				  tr.setSourceAccountNumber(srcAccountNum);
				  tr.setTargetAccountNumber(tarAccountNum); tr.setAmount(amount);
				  tr.setTxnDate( LocalDate.now()); tr.setTransactionType("deposit");
				  transactionRepository.save(tr);
				  
				srcBankAccount.deposit(amount);
				
				if(srcBankAccount instanceof CheckingAccount) {
					System.out.println("Checking Account deposit");
					chAccountRepository.save((CheckingAccount)srcBankAccount);
				}
				if(srcBankAccount instanceof SavingsAccount) {
					System.out.println("Savings Account deposit");
					saAccountRepository.save((SavingsAccount)srcBankAccount);
				}
				return true;
			}
			else {
				// Withdraw
				
				amount = Math.abs(amount);
				  Transaction tr = new WithdrawTransaction();
				  tr.setSourceAccount(srcBankAccount); tr.setTargetAccount(srcBankAccount);
				  tr.setSourceAccountNumber(srcAccountNum);
				  tr.setTargetAccountNumber(tarAccountNum); tr.setAmount(amount);
				  tr.setTxnDate( LocalDate.now()); tr.setTransactionType("withdraw");
				  transactionRepository.save(tr);
				 
				  System.out.println("withdraw amount :: "+amount);
				srcBankAccount.withdraw(amount);
				
				if(srcBankAccount instanceof CheckingAccount) {
					System.out.println("Checking Account withdraw");
					chAccountRepository.save((CheckingAccount)srcBankAccount);
				}
				if(srcBankAccount instanceof SavingsAccount) {
					System.out.println("Savings Account withdraw");
					saAccountRepository.save((SavingsAccount)srcBankAccount);
				}
				return true;
			}
		}
		else {
			// Transfer
			BankAccount tarBankAccount = getBankAccountFromList(tarAccountNum);
			srcBankAccount.withdraw(amount);
			tarBankAccount.deposit(amount);
			if(srcBankAccount instanceof CheckingAccount) {
				System.out.println("Checking Account withdraw");
				chAccountRepository.save((CheckingAccount)srcBankAccount);
			}
			if(srcBankAccount instanceof SavingsAccount) {
				System.out.println("Savings Account withdraw");
				saAccountRepository.save((SavingsAccount)srcBankAccount);
			}
			if(tarBankAccount instanceof CheckingAccount) {
				System.out.println("Checking Account withdraw");
				chAccountRepository.save((CheckingAccount)tarBankAccount);
			}
			if(tarBankAccount instanceof SavingsAccount) {
				System.out.println("Savings Account withdraw");
				saAccountRepository.save((SavingsAccount)tarBankAccount);
			}
			return true;
		}
		
		
		//return false;
	}
	
	public BankAccount getBankAccountFromList(long accountNum) {
		
		for(BankAccount ba : bankAccountList) {
			if(accountNum == ba.getId()) {
				return ba;
			}
		}
		
		return null;
		
	}
}
