package ru.job4j.bank.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.bank.model.Account;
import ru.job4j.bank.model.User;
import ru.job4j.bank.repository.AccountRepository;
import ru.job4j.bank.repository.UserRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleBankService implements BankService{
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;


    @Override
    public void addUser(User user) {
        userRepository.saveOrUpdate(user);
    }

    @Override
    public void addAccount(String passport, String requisite) {

    }

    @Override
    public Optional<User> findByPassport(String passport) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> findByRequisite(String requisite) {
        return Optional.empty();
    }

    @Override
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite,
                                 double amount) {
        return false;
    }
}
