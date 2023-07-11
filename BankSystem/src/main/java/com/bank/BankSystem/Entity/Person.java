package com.bank.BankSystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @NotNull(message = "Name shouldn't be null")
    @NotBlank(message = "Name shouldn't be blank")
    private String name;

    @NotNull(message = "Surname shouldn't be null")
    @NotBlank(message = "Surname shouldn't be blank")
    private String surname;

    @Id
    @NotNull(message = "Personal Number shouldn't be null")
    @NotBlank(message = "Personal number shouldn't be blank")
    @Length(min = 10, max = 10, message = "Invalid personal number")
    private String personalNumber;

    @OneToMany(targetEntity = BankCard.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "PersonCards_association", referencedColumnName = "personalNumber")
    private List<@Valid BankCard> bankCards;

}
