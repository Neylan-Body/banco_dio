package bank.controller.request;


import bank.domain.model.enumerator.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAccountRequest implements Serializable {

    private String agency;

    private BigDecimal balance;

    private String nameClient;

    private AccountTypeEnum accountTypeEnum;

}
