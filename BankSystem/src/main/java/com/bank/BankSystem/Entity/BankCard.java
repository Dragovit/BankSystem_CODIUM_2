package com.bank.BankSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankCard {
    @Id
    @Length(min = 16, max = 16, message = "Invalid card number")
    @NotNull(message = "Card number shouldn't be null")
    @NotBlank(message = "Card number shouldn't be blank")
    private String cardNumber;

    @Length(min = 4, max = 4, message = "Invalid expiration date")
    @NotNull(message = "Expiration date shouldn't be null")
    @NotBlank(message = "Expiration date shouldn't be blank")
    private String expDate;

    @NotNull(message = "Card holder shouldn't be null")
    @NotBlank(message = "Card holder shouldn't be blank")
    private String cardHolder;

    @Length(min = 3, max = 3, message = "Invalid cvc")
    @NotNull(message = "Cvc shouldn't be null")
    @NotBlank(message = "Cvc shouldn't be blank")
    private String cvc;

}
