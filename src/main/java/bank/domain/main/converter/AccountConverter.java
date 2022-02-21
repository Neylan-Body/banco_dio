package bank.domain.main.converter;

import bank.controller.request.CreateAccountRequest;
import bank.controller.response.AccountResponse;
import bank.domain.model.Account;
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

    public Account toAccount(CreateAccountRequest createAccountRequest) {
        return this.mapper.map(createAccountRequest, Account.class);
    }

}
