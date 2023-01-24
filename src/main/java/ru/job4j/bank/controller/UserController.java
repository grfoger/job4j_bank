package ru.job4j.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.job4j.bank.model.User;
import ru.job4j.bank.service.BankService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getSimpleName());
    private final BankService bankService;
    private final ObjectMapper objectMapper;


    @PostMapping
    public User save(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        if (username == null || password == null) {
            throw new NullPointerException("Username and password mustn't be empty");
        }
        if(password.length() < 6) {
            throw new IllegalArgumentException("Invalid password. Password length bust be more then 5 characters.");
        }
        User user = new User(username, password);
        bankService.addUser(user);
        return user;
    }

    @GetMapping
    public User findByPassport(@RequestParam String passport) {
        return bankService.findByPassport(passport).orElse(null);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(Map.ofEntries(
         entry("message", e.getMessage()),
         entry("type", e.getClass())
        )));
        LOGGER.error(e.getLocalizedMessage());
    }
}
