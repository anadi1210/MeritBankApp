package com.meritbank.v1.meritBankV1.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritbank.v1.meritBankV1.models.AccountHolder;
import com.meritbank.v1.meritBankV1.models.CDAccount;
import com.meritbank.v1.meritBankV1.models.CDOffering;
import com.meritbank.v1.meritBankV1.models.CheckingAccount;
import com.meritbank.v1.meritBankV1.models.SavingsAccount;
import com.meritbank.v1.meritBankV1.models.User;
import com.meritbank.v1.meritBankV1.repos.AccountHolderRepository;
import com.meritbank.v1.meritBankV1.repos.CDAccountRepository;
import com.meritbank.v1.meritBankV1.repos.CDOfferingRepository;
import com.meritbank.v1.meritBankV1.repos.CheckingAccountRepository;
import com.meritbank.v1.meritBankV1.repos.SavingsAccountRepository;
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
	
	
	private static final double checkingInterestRate = 0.0001;
	private static final double savingInterestRate = 0.01;
	private static final int MAX_COMBINED_BALANCE = 250000;
	
	public CheckingAccount addCheckingAccount(int accountHolderId, double balance) {
		
		CheckingAccount chAccount = new CheckingAccount();
		chAccount.setBalance(balance);
		chAccount.setCheckingInterestRate(checkingInterestRate);
		chAccount.setAccountHolderId(accountHolderId);
		chAccount.setOpenedOn(LocalDateTime.now());
		accHolderRepository.findById(accountHolderId).setCheckingAccount(chAccount);
		return chAccountRepository.save(chAccount);
	}
	
	public CheckingAccount getCheckingAccount(long id) {
		
		return accHolderRepository.getById(id).getCheckingAccount();
	}
	
	public SavingsAccount addSavingsAccount(int accountHolderId, double balance) {
		
		SavingsAccount saAccount = new SavingsAccount();
		saAccount.setBalance(balance);
		saAccount.setSavingsInterestRate(savingInterestRate);
		saAccount.setAccountHolderId(accountHolderId);
		
		saAccount.setOpenedOn(LocalDateTime.now());
		accHolderRepository.findById(accountHolderId).setSavingsAccounts(saAccount);
		return saAccountRepository.save(saAccount);
	}
	
	public SavingsAccount getSavingsAccount(long id) {
		
		return accHolderRepository.getById(id).getSavingsAccounts();
	}
	
	public CDAccount addCDAccount(int accountHolderId, double balance, int cdOfferingId) {
		
		CDOffering offering =  cdOfferingRepository.findById(cdOfferingId).get();
		
		CDAccount cdAccount = new CDAccount(offering, balance);
		cdAccount.setOpenedOn(LocalDateTime.now());
		cdAccount.setAccountHolderId(accountHolderId);
		accHolderRepository.findById(accountHolderId).getCdAccountsList().add(cdAccount);
		return cdAccountRepository.save(cdAccount);
	}	
	
	public List<CDAccount> getCDAccount(long id) {
		
		return cdAccountRepository.findAll();
	}
	
	public List<CDAccount> getCDAccountForAccountHolder(long accountHolderId) {
		
		return accHolderRepository.findById(accountHolderId).getCdAccountsList();
	}
}
