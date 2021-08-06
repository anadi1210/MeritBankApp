package com.meritbank.v1.meritBankV1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PostMapping("/AccountHolders/{id}/CheckingAccounts")
	public CheckingAccount addCheckingAccount(@PathVariable int id, @RequestBody CheckingAccount ca) {
		
		return meritBankService.addCheckingAccount(id, ca.getBalance());
	}
	
	
	@GetMapping("/AccountHolders/{id}/CheckingAccounts")
	public CheckingAccount getCheckingAccount(@PathVariable int id) {
		
		return meritBankService.getCheckingAccount(id);
	}
	
	
	@PostMapping("/AccountHolders/{id}/SavingsAccounts")
	public SavingsAccount addSavingsAccount(@PathVariable int id, @RequestBody SavingsAccount sa) {
	
		return meritBankService.addSavingsAccount(id, sa.getBalance());
	}
	
	
	@GetMapping("/AccountHolders/{id}/SavingsAccount")
	public SavingsAccount getSavingsAccount(@PathVariable int id) {
		
		return meritBankService.getSavingsAccount(id);
	}
	
	
	@PostMapping("/AccountHolders/{id}/CDAccounts") 
	public CDAccount addCDAccount(@PathVariable int id, @RequestBody CDAccount cdAccount) {
		
		return meritBankService.addCDAccount(id, cdAccount.getBalance(), cdAccount.getCdOffering().getCdOffereing_Id()); 
	}
	  
	  
	@GetMapping("/AccountHolders/{id}/CDAccounts") 
	public List<CDAccount> getCDAccount(@PathVariable int id) {
	  
	  return meritBankService.getCDAccountForAccountHolder(id); 
	}
}
