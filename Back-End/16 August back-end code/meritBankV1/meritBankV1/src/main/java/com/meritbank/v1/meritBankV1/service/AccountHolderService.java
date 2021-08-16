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
		u.setAccountHolder(ah);
		ah.setUser(u);
		return accountHolderRepository.save(ah);
		
		//return ah;
	}
	
	public AccountHolder getAccountHolder(int id) {
		AccountHolder ah = accountHolderRepository.findById(id);
		return ah;
	}
	public AccountHolder getAccountHolder(String name) {
		AccountHolder ah = accountHolderRepository.findByUsername(name);
		return ah;
	}
	
	public List<AccountHolder> getAllAccountHolders() {
		List<AccountHolder> ahList = accountHolderRepository.findAll();
		return ahList;
	}
	
	public String deleteAccountHolder(long id) {
		accountHolderRepository.deleteById(id);
		
		return "User Deleted";
		//return null;
	}
	
	public boolean hasCheckingAccount(long id) {
		double balance = accountHolderRepository.getById(id).getCheckingAccount().getBalance();
		if(balance != 0) {
			return false;
		}
		return true;
	}
	
	public boolean hasSavingsAccount(long id) {
		double balance = accountHolderRepository.getById(id).getSavingsAccounts().getBalance();
		if(balance != 0) {
			return false;
		}
		return true;
	}
	
	public boolean hasCDAccount(long id) {
		int cdAccountListSize = accountHolderRepository.getById(id).getCdAccountsList().size();
		if(cdAccountListSize != 0) {
			return false;
		}
		return true;
	}
}
