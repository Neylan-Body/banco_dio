package bank.controller;

import bank.controller.request.CreateBankRequest;
import bank.controller.response.BankResponse;
import bank.controller.response.ClientForBankResponse;
import bank.domain.main.converter.BankConverter;
import bank.domain.main.converter.ClientConverter;
import bank.domain.model.Bank;
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

    private final BankConverter bankConverter;

    private final ClientConverter clientConverter;

    private final BankRepository bankRepository;

    @GetMapping("/")
    public List<BankResponse> getBanks(){
        return bankConverter.toListBankResponse(bankRepository.findAll());
    }

    @GetMapping("/{name}/clients")
    public List<ClientForBankResponse> getClients(@PathVariable("name") String name){
        return clientConverter.toListClientForBankResponse(bankRepository.findClientsByName(name));
    }

    @GetMapping("/{name}")
    public BankResponse getBankByName(@PathVariable("name") String name){
        return bankConverter.toBankResponse(bankRepository.findByName(name));
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