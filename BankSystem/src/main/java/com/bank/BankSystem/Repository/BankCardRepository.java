package com.bank.BankSystem.Repository;

import com.bank.BankSystem.Entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard, Integer> {
}
