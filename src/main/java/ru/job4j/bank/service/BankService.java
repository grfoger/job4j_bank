package ru.job4j.bank.service;


import ru.job4j.bank.model.Account;
import ru.job4j.bank.model.User;

import java.util.Optional;

public interface BankService {
    void addUser(User user);
    void addAccount(String passport, String requisite);
    Optional<User> findByPassport(String passport);
    Optional<Account> findByRequisite(String requisite);
    boolean transferMoney(String srcPassport, String srcRequisite,
                          String destPassport, String destRequisite, double amount);
}
