package bank.controller;

import bank.controller.request.CreateBankRequest;
import bank.controller.response.BankResponse;
import bank.controller.response.ClientForBankResponse;
import bank.domain.main.converter.BankConverter;
import bank.domain.main.converter.ClientConverter;
import bank.domain.model.Bank;
import bank.repository.BankRepository;
import bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bank")
@RequiredArgsConstructor
@RestController
public class BankController {

    private final BankService bankService;

    private final BankConverter bankConverter;

    private final ClientConverter clientConverter;

    private final BankRepository bankRepository;

    @GetMapping("/")
    public ResponseEntity<List<BankResponse>> getBanks(){
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findAllBanks());
    }

    @GetMapping("/{name}/clients")
    public ResponseEntity<List<ClientForBankResponse>> getClients(@PathVariable("name") String name){
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findClientsByNameBank(name));
    }

    @GetMapping("/{name}")
    public ResponseEntity<BankResponse> getBankByName(@PathVariable("name") String name){
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findBankByName(name));
    }

    @PostMapping("/")
    public ResponseEntity<Void> createBank(@RequestBody CreateBankRequest createBankRequest){
        bankService.createBank(createBankRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{name}")
    @Transactional
    public ResponseEntity<Void> deleteBankByName(@PathVariable("name") String name){
        bankService.deleteBankByName(name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}