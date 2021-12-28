package bank.controller;

import bank.model.Bank;
import bank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {

    private final BankRepository bankRepository;

    @GetMapping("/")
    public List<Bank> getBanks(){
        return bankRepository.findAll();
    }
}