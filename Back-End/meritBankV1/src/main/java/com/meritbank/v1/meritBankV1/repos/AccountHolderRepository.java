package com.meritbank.v1.meritBankV1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meritbank.v1.meritBankV1.models.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

	public AccountHolder findById(long id);
}
