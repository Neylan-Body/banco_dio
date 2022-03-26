package bank.controller.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankResponse {

    private String name;

    private List<ClientForBankResponse> clients;
}
