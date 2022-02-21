package bank.domain.main.converter;

import bank.controller.request.CreateAccountRequest;
import bank.controller.response.AccountResponse;
import bank.domain.model.Account;
import bank.domain.model.CurrentAccount;
import bank.domain.model.SavingsAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountConverter {

    private final ModelMapper mapper;

    public AccountResponse toAccountResponse(Account account) {
        return this.mapper.map(account, AccountResponse.class);
    }

    public CurrentAccount toCurrentAccount(CreateAccountRequest createAccountRequest) {
        return this.mapper.map(createAccountRequest, CurrentAccount.class);
    }

    public SavingsAccount toSavingsAccount(CreateAccountRequest createAccountRequest) {
        return this.mapper.map(createAccountRequest, SavingsAccount.class);
    }

}
