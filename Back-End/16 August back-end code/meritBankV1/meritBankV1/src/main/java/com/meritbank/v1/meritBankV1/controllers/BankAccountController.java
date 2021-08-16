package com.meritbank.v1.meritBankV1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritbank.v1.meritBankV1.models.AccountHolder;
import com.meritbank.v1.meritBankV1.models.CDAccount;
import com.meritbank.v1.meritBankV1.models.CDOffering;
import com.meritbank.v1.meritBankV1.models.CheckingAccount;
import com.meritbank.v1.meritBankV1.models.SavingsAccount;
import com.meritbank.v1.meritBankV1.models.Transaction;
import com.meritbank.v1.meritBankV1.repos.AccountHolderRepository;
import com.meritbank.v1.meritBankV1.repos.AccountHoldersContactDetailsRepository;
import com.meritbank.v1.meritBankV1.repos.CDAccountRepository;
import com.meritbank.v1.meritBankV1.repos.CDOfferingRepository;
import com.meritbank.v1.meritBankV1.repos.CheckingAccountRepository;
import com.meritbank.v1.meritBankV1.repos.SavingsAccountRepository;
import com.meritbank.v1.meritBankV1.service.MeritBankService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BankAccountController {

	
	/*
	 * Need to implement the delete feature for each of the account type
	 * 
	 */
	
	
	@Autowired 
	AccountHolderRepository accountRepo;

	@Autowired
	CDAccountRepository cdAccountRepository;
	@Autowired
	CheckingAccountRepository checkingAccountRepository;
	@Autowired
	SavingsAccountRepository savingsAccountRepository;
	@Autowired
	CDOfferingRepository cdOfferingRepo;
	@Autowired
	AccountHoldersContactDetailsRepository contactDetailsRepo;
	
	@Autowired
	MeritBankService meritBankService;
	
	public long getAccountHolderId(String name) {
		long id = accountRepo.findByUsername(name).getAccountHolderId();
		
		return id;
	}
	
	@PostMapping("/AccountHolders/{name}/CheckingAccounts")
	public CheckingAccount addCheckingAccount(@PathVariable String name, @RequestBody CheckingAccount ca) {
		long id = getAccountHolderId(name);
		System.out.println("Balance passed is :: "+ca.getBalance());
		return meritBankService.addCheckingAccount(id, ca.getBalance());
	}
	
	@GetMapping("/AccountHolders/{name}/CheckingAccounts")
	public CheckingAccount getCheckingAccount(@PathVariable String name) {
		long id = getAccountHolderId(name);
		return meritBankService.getCheckingAccount(id);
	}
	
	@DeleteMapping("/AccountHolders/{name}/CheckingAccounts")
	public String deleteCheckingAccount(@PathVariable String name) {
		long id = getAccountHolderId(name);
		double balance = accountRepo.findById(id).getCheckingAccount().getBalance();
		if(balance != 0) {
			return "Account can not deleted, account has non zero balance";
		}
		return "Account deleted ";
	}
	
	@PostMapping("/AccountHolders/{name}/SavingsAccounts")
	public SavingsAccount addSavingsAccount(@PathVariable String name, @RequestBody SavingsAccount sa) {
		long id = getAccountHolderId(name);
		return meritBankService.addSavingsAccount(id, sa.getBalance());
	}
	
	@GetMapping("/AccountHolders/{name}/SavingsAccount")
	public SavingsAccount getSavingsAccount(@PathVariable String name) {
		long id = getAccountHolderId(name);
		return meritBankService.getSavingsAccount(id);
	}
	
	@DeleteMapping("/AccountHolders/{name}/SavingsAccount")
	public String deleteSavingsAccount(@PathVariable String name) {
		long id = getAccountHolderId(name);
		double balance = accountRepo.findById(id).getSavingsAccounts().getBalance();
		if(balance != 0) {
			return "Account can not deleted, account has non zero balance";
		}
		return "Account deleted ";
	}
	
	@PostMapping("/AccountHolders/{name}/CDAccounts") 
	public CDAccount addCDAccount(@PathVariable String name, @RequestBody CDAccount cdAccount) {
		long id = getAccountHolderId(name);
		return meritBankService.addCDAccount(id, cdAccount.getBalance(), cdAccount.getCdOffering().getCdOffereing_Id()); 
	}
	  
	  
	@GetMapping("/AccountHolders/{name}/CDAccounts") 
	public List<CDAccount> getCDAccount(@PathVariable String name) {
		long id = getAccountHolderId(name);
	  return meritBankService.getCDAccountForAccountHolder(id); 
	}
	
	@DeleteMapping("/AccountHolders/{name}/CDAccounts")
	public String deleteCDAccount(@PathVariable String name) {
		long id = getAccountHolderId(name);
		double balance =  accountRepo.findById(id).getCdAccountsList().get(0).getBalance();
		if(balance != 0) {
			return "Account can not deleted, one or more CD Account/s has non zero balance";
		}
		else {
			cdAccountRepository.deleteAll();
			return "Account deleted ";
		}
		
	}
	
	@PostMapping("/AccountHolders/{name}/Transaction")
	public String postTransaction(@PathVariable String name, @RequestBody Transaction transaction){
		long id = getAccountHolderId(name);
		boolean flag  = meritBankService.processTransaction(id, transaction);
		
		if(flag)
			return "success";
		else
			return "Failed";
	}
		
}
