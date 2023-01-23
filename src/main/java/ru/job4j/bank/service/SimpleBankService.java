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
    public void addAccount(String passport, Account account) {
        userRepository.findByPassport(passport).ifPresent(u -> {
            account.setUser(u);
            accountRepository.saveOrUpdate(account);
            u.getAccounts().add(account);
        });
    }

    @Override
    public Optional<User> findByPassport(String passport) {
        return userRepository.findByPassport(passport);
    }

    @Override
    public Optional<Account> findByRequisite(String passport, String requisite) {
        return accountRepository.findByRequisite(passport, requisite);
    }

    @Override
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite,
                                 double amount) {
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        if (srcAccount.isEmpty()) {
            return false;
        }
        Optional<Account> destAccount = findByRequisite(srcPassport, srcRequisite);
        if (destAccount.isEmpty()) {
            return false;
        }
        if (srcAccount.get().getBalance() - amount < 0) {
            return false;
        }
        srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
        destAccount.get().setBalance(destAccount.get().getBalance() + amount);
        return true;
    }
}
