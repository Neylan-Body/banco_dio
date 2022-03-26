package bank.controller.response;

import bank.domain.model.enumerator.AccountTypeEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountResponse {

    private Integer id;

    private Integer agency;

    private Integer accountNumber;

    private String nameClient;

    private AccountTypeEnum accountTypeEnum;

    private BigDecimal balance;
}
