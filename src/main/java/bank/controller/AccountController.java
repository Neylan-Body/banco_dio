package bank.controller;

import bank.controller.request.CreateAccountRequest;
import bank.controller.response.AccountResponse;
import bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/account")
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance/{agency}")
    public ResponseEntity<String> getBalance(@PathVariable("agency") Integer agency, @RequestParam(name = "accountNumber") Integer accountNumber){
        var balance = accountService.getBalance(agency, accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }

    @PostMapping("/")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        var account = accountService.createAccount(createAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }
}
