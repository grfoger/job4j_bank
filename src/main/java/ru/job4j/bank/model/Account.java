package ru.job4j.bank.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Account extends Id{
    private String requisite;
    private double balance;
    private User user;
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }
}
