package bank.service;

import bank.controller.request.CreateAccountRequest;
import bank.controller.request.DepositCashRequest;
import bank.controller.request.TransferCashRequest;
import bank.controller.request.WithdrawMoneyRequest;
import bank.controller.response.AccountResponse;
import bank.domain.model.Account;
import bank.domain.model.Bank;
import bank.domain.model.Client;
import bank.domain.model.enumerator.AccountTypeEnum;
import bank.domain.model.enumerator.Message;
import bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponse createAccount(CreateAccountRequest createAccountRequest){
        var account = Account.builder()
                .agency(createAccountRequest.getAgency())
                .balance(createAccountRequest.getBalance())
                .bank(Bank.builder()
                        .name(createAccountRequest.getBank().getName())
                        .build())
                .client(Client.builder()
                        .name(createAccountRequest.getClient().getName())
                        .build())
                .build();
        if (createAccountRequest.getAccountType().equals(AccountTypeEnum.CURRENT)) {
            account.setAccountTypeEnum(AccountTypeEnum.CURRENT);
        } else {
            account.setAccountTypeEnum(AccountTypeEnum.SAVINGS);
        }
        account = accountRepository.save(account);

        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .accountTypeEnum(account.getAccountTypeEnum())
                .agency(account.getAgency())
                .id(account.getId())
                .balance(account.getBalance())
                .nameClient(account.getClient().getName())
                .build();
    }

    public String getBalance(Integer agency, Integer accountNumber){
        var account = accountRepository
                .findByAgencyAndAccountNumber(agency, accountNumber)
                .orElseThrow(() -> {
                    throw Message.ACCOUNT_NOT_FOUND.asBusinessException(
                            agency.toString(),
                            accountNumber.toString());
                });
        return "R$ "+account.getBalance().toString().replace(".", ",");
    }

    public String transferCash(TransferCashRequest transferCashRequest) {
        withdraw(transferCashRequest.getAccountNumberOrigin(),
                transferCashRequest.getAgencyOrigin(),
                transferCashRequest.getBalance());

        deposit(transferCashRequest.getAccountNumberDestiny(),
                transferCashRequest.getAgencyDestiny(),
                transferCashRequest.getBalance());

        return "Valor "+getBalanceInBrazilianReal(transferCashRequest.getBalance())+" transferido";
    }

    public String depositCash(DepositCashRequest depositCashRequest) {
        deposit(depositCashRequest.getAccountNumberDestiny(),
                depositCashRequest.getAgencyDestiny(),
                depositCashRequest.getBalance());

        return "Valor "+getBalanceInBrazilianReal(depositCashRequest.getBalance())+" depositado";
    }

    public String withdrawMoney(WithdrawMoneyRequest withdrawMoneyRequest) {
        withdraw(withdrawMoneyRequest.getAccountNumberDestiny(), 
                withdrawMoneyRequest.getAgencyDestiny(), 
                withdrawMoneyRequest.getBalance());

        return "Valor " + getBalanceInBrazilianReal(withdrawMoneyRequest.getBalance()) + " sacado";
    }

    private String getBalanceInBrazilianReal(BigDecimal balance) {
        return "R$ " + balance.toString().replace(".", ",") + "";
    }

    private Account getAccountByAccountNumberAndAgency(Integer accountNumber, Integer agency){
        return accountRepository
                .findByAgencyAndAccountNumber(agency, accountNumber)
                .orElseThrow(() -> {
                    throw Message.ACCOUNT_NOT_FOUND.asBusinessException(
                            accountNumber.toString(),
                            agency.toString());
                });
    }
    
    private void withdraw(Integer accountNumber, Integer agency, BigDecimal balance){
        var account = getAccountByAccountNumberAndAgency(
                accountNumber, agency);
        if (account.getBalance().doubleValue() < balance.doubleValue()){
            throw Message.INSUFFICIENT_FUNDS.asBusinessException();
        }
        account.setBalance(account.getBalance().subtract(balance));
        accountRepository.save(account);
    }

    private void deposit(Integer accountNumber, Integer agency, BigDecimal balance){
        var account = getAccountByAccountNumberAndAgency(
                accountNumber, agency);
        account.setBalance(account.getBalance().add(balance));
        accountRepository.save(account);
    }
}
