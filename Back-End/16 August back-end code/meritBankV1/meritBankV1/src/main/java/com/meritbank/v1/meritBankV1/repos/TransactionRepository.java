package com.meritbank.v1.meritBankV1.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank.v1.meritBankV1.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
