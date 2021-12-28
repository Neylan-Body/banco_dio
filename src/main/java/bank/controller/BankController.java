package bank.controller;

import bank.controller.request.CreateBankRequest;
import bank.model.Bank;
import bank.model.Client;
import bank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/bank")
@RequiredArgsConstructor
@RestController
public class BankController {

    private final BankRepository bankRepository;

    @GetMapping("/")
    public List<Bank> getBanks(){
        return bankRepository.findAll();
    }

    @GetMapping("/{name}/clients")
    public List<Client> getClients(@PathVariable("name") String name){
        return bankRepository.findClientsByName(name);
    }

    @GetMapping("/{name}")
    public Bank getBankByName(@PathVariable("name") String name){
        return bankRepository.findByName(name);
    }

    @PostMapping("/")
    public void createBank(@RequestBody CreateBankRequest createBankRequest){
        bankRepository.save(Bank.builder().name(createBankRequest.getName()).build());
    }

    @DeleteMapping("/{name}")
    @Transactional
    public void deleteBankByName(@PathVariable("name") String name){
        bankRepository.deleteByName(name);
    }
}