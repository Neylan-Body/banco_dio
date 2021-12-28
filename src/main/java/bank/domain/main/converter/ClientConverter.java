package bank.domain.main.converter;

import bank.controller.response.ClientResponse;
import bank.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientConverter {

    private final ModelMapper mapper;

    public List<ClientResponse> toListClientResponse(List<Client> clients) {
        List<ClientResponse> clientResponses = new ArrayList<>();
        clients.forEach(client -> clientResponses.add(toClientResponse(client)));
        return clientResponses;
    }

    public ClientResponse toClientResponse(Client client) {
        return this.mapper.map(client, ClientResponse.class);
    }

}
