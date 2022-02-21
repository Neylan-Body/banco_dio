package bank.controller.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateBankRequest implements Serializable {

    @NotNull(message = "Campo name é obrigatório")
    private String name;

}
