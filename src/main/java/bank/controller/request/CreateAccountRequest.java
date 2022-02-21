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

    @NotNull(message = "Campo client é obrigatório")
    private CreateClientRequest client;

    @NotNull(message = "Campo bank é obrigatório")
    private CreateBankRequest bank;

    @NotNull(message = "Campo accountType é obrigatório")
    private AccountTypeEnum accountType;

}
