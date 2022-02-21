package bank.service;

import bank.controller.request.CreateAccountRequest;
import bank.controller.response.AccountResponse;
import bank.domain.model.Account;
import bank.domain.model.Bank;
import bank.domain.model.Client;
import bank.domain.model.enumerator.AccountTypeEnum;
import bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        var account = accountRepository.findByAgencyAndAccountNumber(agency, accountNumber);
        return "R$ "+account.getBalance().toString().replace(".", ",");
    }

}
