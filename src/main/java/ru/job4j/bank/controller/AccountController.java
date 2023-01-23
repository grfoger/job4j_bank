package ru.job4j.bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.job4j.bank.model.Account;
import ru.job4j.bank.service.BankService;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
    private final BankService bankService;

    @PostMapping
    public Account addAccount(@RequestBody Map<String, String> body) {
        String passport = body.get("passport");
        Account account = new Account(body.get("requisite"), 0);
        bankService.addAccount(passport, account);
        return account;
    }

    @GetMapping
    public Account findByRequisite(@RequestParam String passport, @RequestParam String requisite) {
        return bankService.findByRequisite(passport, requisite).orElse(null);
    }
}
