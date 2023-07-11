package com.bank.BankSystem.Repository;

import com.bank.BankSystem.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
