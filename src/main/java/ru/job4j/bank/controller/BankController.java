package ru.job4j.bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.bank.service.BankService;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;

    @PostMapping
    public void transfer(@RequestBody Map<String, String> body) {
        String srcPassport = body.get("srcPassport");
        String srcRequisite = body.get("srcRequisite");
        String destPassport = body.get("srcPassport");
        String destRequisite = body.get("srcRequisite");
        double amount = Double.parseDouble(body.get("amount"));
        bankService.transferMoney(srcPassport, srcRequisite,
                                    destPassport, destRequisite,
                                    amount);
    }
}
