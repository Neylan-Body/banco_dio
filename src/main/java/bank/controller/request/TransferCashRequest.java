package bank.controller.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransferCashRequest implements Serializable {

    @NotNull(message = "Campo agencyOrigin é obrigatório")
    private Integer agencyOrigin;

    @NotNull(message = "Campo accountNumberOrigin é obrigatório")
    private Integer accountNumberOrigin;

    @NotNull(message = "Campo agencyDestiny é obrigatório")
    private Integer agencyDestiny;

    @NotNull(message = "Campo accountNumberDestiny é obrigatório")
    private Integer accountNumberDestiny;

    @NotNull(message = "Campo balance é obrigatório")
    private BigDecimal balance = BigDecimal.ZERO;
}
