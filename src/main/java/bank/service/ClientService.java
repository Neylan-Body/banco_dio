package bank.service;

import bank.controller.request.CreateClientRequest;
import bank.controller.request.UpdateClientAddBankRequest;
import bank.controller.response.ClientResponse;
import bank.domain.main.converter.ClientConverter;
import bank.domain.model.Client;
import bank.domain.model.enumerator.Message;
import bank.repository.BankRepository;
import bank.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;
    private final BankRepository bankRepository;

    public ClientResponse createClient(CreateClientRequest createClientRequest) {
        var client = clientRepository.save(Client.builder().name(createClientRequest.getName()).build());
        return ClientResponse.builder()
                .name(client.getName())
                .build();
    }

    public List<ClientResponse> findAllClients() {
        return clientConverter.toListClientResponse(clientRepository.findAll());
    }

    public List<ClientResponse> findClientsByName(String name) {
        List<Client> clients = clientRepository.findByName(name)
                .orElseThrow(() -> Message.CLIENT_WITH_NAME_NOT_FOUND.asBusinessException(name));
        return clientConverter.toListClientResponse(clients);
    }

    public String deleteClientsByName(String name) {
        boolean exists = clientRepository.existsClientByName(name);
        if (exists) {
            clientRepository.deleteByName(name);
            return "Clientes com o nome " + name + " foram deletados";
        }
        throw Message.CLIENT_WITH_NAME_NOT_FOUND.asBusinessException(name);
    }

    public void addBank(Integer clientId, UpdateClientAddBankRequest updateClientAddBankRequest) {
        var bank = bankRepository.findByName(updateClientAddBankRequest.getNameBank())
                .orElseThrow(() -> Message.BANK_WITH_NAME_NOT_FOUND.asBusinessException(updateClientAddBankRequest.getNameBank()));
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> Message.CLIENT_WITH_ID_NOT_FOUND.asBusinessException(clientId));
        bank.addClient(client);
        clientRepository.save(client);
    }
}
