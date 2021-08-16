package com.meritbank.v1.meritBankV1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritbank.v1.meritBankV1.models.Transaction;
import com.meritbank.v1.meritBankV1.repos.AccountHolderRepository;
import com.meritbank.v1.meritBankV1.service.MeritBankService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

	@Autowired
	MeritBankService meritBankService;
	@Autowired
	AccountHolderRepository accountRepo;
	
	@PostMapping("/AccountHolders/{name}/transaction")
	public String postTransaction(@PathVariable String name, @RequestBody Transaction tr){
		long id = getAccountHolderId(name);
		boolean resp = meritBankService.processTransaction(id, tr);
		if(resp)
			return "success";
		else
			return "Failed";
	}
	
	public long getAccountHolderId(String name) {
		long id = accountRepo.findByUsername(name).getAccountHolderId();
		
		return id;
	}
}
