package com.bank.BankSystem.Services;

import com.bank.BankSystem.Entity.Person;

import java.util.List;

public interface PersonService {
    public Person savePerson(Person person);
    public List<Person> findAll();
}
