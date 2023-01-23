package ru.job4j.bank.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
@EqualsAndHashCode
public class User extends Id{
    private String passport;
    private String username;
    private List<Account> accounts = new CopyOnWriteArrayList<>();

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

}
