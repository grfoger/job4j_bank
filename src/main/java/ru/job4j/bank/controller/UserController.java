package ru.job4j.bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.job4j.bank.model.User;
import ru.job4j.bank.service.BankService;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final BankService bankService;

    @PostMapping
    public User save(@RequestBody Map<String, String> body) {
        User user = new User(body.get("username"), body.get("password"));
        bankService.addUser(user);
        return user;
    }

    @GetMapping
    public User findByPassport(@RequestParam String passport) {
        return bankService.findByPassport(passport).orElse(null);
    }
}
