package com.meritbank.v1.meritBankV1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritbank.v1.meritBankV1.models.AccountHolder;
import com.meritbank.v1.meritBankV1.models.User;
import com.meritbank.v1.meritBankV1.repos.AccountHolderRepository;

@Service
public class AccountHolderService {

	@Autowired
	AccountHolderRepository accountHolderRepository;
	
	public AccountHolder addAccountHolder(AccountHolder ah) {
		User u  = new User(ah.getUsername(),ah.getPassword(), true, "USER");
		ah.setUser(u);
		accountHolderRepository.save(ah);
		return ah;
	}
	
	public AccountHolder getAccountHolder(int id) {
		AccountHolder ah = accountHolderRepository.findById(id);
		return ah;
	}
	
	public List<AccountHolder> getAllAccountHolders() {
		List<AccountHolder> ahList = accountHolderRepository.findAll();
		return ahList;
	}
}
