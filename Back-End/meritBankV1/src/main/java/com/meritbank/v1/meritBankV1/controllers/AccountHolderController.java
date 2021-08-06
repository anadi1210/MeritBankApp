package com.meritbank.v1.meritBankV1.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritbank.v1.meritBankV1.models.AccountHolder;
import com.meritbank.v1.meritBankV1.models.AccountHoldersContactDetails;

import com.meritbank.v1.meritBankV1.repos.AccountHolderRepository;
import com.meritbank.v1.meritBankV1.repos.AccountHoldersContactDetailsRepository;
import com.meritbank.v1.meritBankV1.repos.CDAccountRepository;
import com.meritbank.v1.meritBankV1.repos.CDOfferingRepository;
import com.meritbank.v1.meritBankV1.repos.CheckingAccountRepository;
import com.meritbank.v1.meritBankV1.repos.SavingsAccountRepository;
import com.meritbank.v1.meritBankV1.service.AccountHolderService;
import com.meritbank.v1.meritBankV1.service.MeritBankService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountHolderController {

	//protected List<AccountHolder> accounHoldersList = new ArrayList<AccountHolder>();
	
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
	AccountHolderService accountHolderService;
	
	@PostMapping("/AccountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
	
		
		return accountHolderService.addAccountHolder(accountHolder);
		
	}
	
	@GetMapping("/AccountHolders")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountHolder> getAccountHolders() {
		
		return accountHolderService.getAllAccountHolders();
	}
	
	@GetMapping("/AccountHolders/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountHolder getAccountHolder(@PathVariable (name = "id") int id) {
		
		return accountHolderService.getAccountHolder(id);//.get(); 
	}

	/*
	 * @PostMapping("/AccountHolders/{id}/contact")
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public AccountHoldersContactDetails
	 * addContact(@PathVariable int id, @RequestBody @Valid
	 * AccountHoldersContactDetails contactDetails) {
	 * 
	 * 
	 * return contactDetails; }
	 * 
	 * @GetMapping("/AccountHolders/{id}/contact") public
	 * AccountHoldersContactDetails getContact(@PathVariable int id) {
	 * 
	 * 
	 * return null; }
	 */
}