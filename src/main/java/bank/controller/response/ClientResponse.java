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
public class ClientResponse {

    private String name;

    private List<BankForClientResponse> banks;
}
