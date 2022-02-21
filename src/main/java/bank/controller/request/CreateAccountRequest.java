package bank.controller.request;


import bank.domain.model.enumerator.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAccountRequest implements Serializable {

    @NotNull(message = "Campo agency é obrigatório")
    private Integer agency;

    @NotNull(message = "Campo balance é obrigatório")
    private BigDecimal balance;

    @NotNull(message = "Campo nameClient é obrigatório")
    private String nameClient;

    @NotNull(message = "Campo accountTypeEnum é obrigatório")
    private AccountTypeEnum accountTypeEnum;

}
