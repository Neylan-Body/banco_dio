package bank.controller;

import bank.controller.request.CreateAccountRequest;
import bank.controller.response.AccountResponse;
import bank.domain.main.converter.AccountConverter;
import bank.domain.model.Account;
import bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account")
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    private final AccountConverter accountConverter;


    @GetMapping("/balance/{agency}")
    public String getBalance(@PathVariable("agency") String agency, @RequestParam(name = "accountNumber") String accountNumber){
        var account = accountRepository.findByAgencyAndAccountNumber(agency, accountNumber);
        return "R$ "+account.getBalance().toString().replace(".", ",");
    }

    @PostMapping("/")
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest){
        var account = accountConverter.toAccount(createAccountRequest);
        var accountSaved = accountRepository.save(account);
        return accountConverter.toAccountResponse(accountSaved);
    }

}
