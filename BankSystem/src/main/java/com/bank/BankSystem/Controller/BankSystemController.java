package com.bank.BankSystem.Controller;

import com.bank.BankSystem.Entity.Person;
import com.bank.BankSystem.Repository.BankCardRepository;
import com.bank.BankSystem.Repository.PersonRepository;
import com.bank.BankSystem.Services.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankSystemController {
    @Autowired
    private PersonService personService;

    Logger logger = LoggerFactory.getLogger(BankSystemController.class);


    @PostMapping("/addPerson")
    public ResponseEntity<String> addPerson(@RequestBody @Valid Person person) {

        this.personService.savePerson(person);
        logger.debug("Je zapnuty LOCAL profil");
        logger.info("Je zapnuty PRODUCTION profil");
        return ResponseEntity.ok("Person successfully added!");

    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Person>> findAll() {
        logger.debug("Je zapnuty LOCAL profil");
        logger.info("Je zapnuty PRODUCTION profil");
        return ResponseEntity.ok(this.personService.findAll());
    }

}
