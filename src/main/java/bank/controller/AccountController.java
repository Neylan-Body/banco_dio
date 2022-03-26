package bank.controller;

import bank.controller.request.CreateAccountRequest;
import bank.controller.request.DepositCashRequest;
import bank.controller.request.TransferCashRequest;
import bank.controller.request.WithdrawMoneyRequest;
import bank.controller.response.AccountResponse;
import bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/transfer")
    public ResponseEntity<String> transferCash(@Valid @RequestBody TransferCashRequest transferCashRequest){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.transferCash(transferCashRequest));
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositCash(@Valid @RequestBody DepositCashRequest depositCashRequest){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.depositCash(depositCashRequest));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@Valid @RequestBody WithdrawMoneyRequest withdrawMoneyRequest){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.withdrawMoney(withdrawMoneyRequest));
    }
}
