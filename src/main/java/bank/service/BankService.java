package bank.service;

import bank.controller.request.CreateBankRequest;
import bank.controller.request.CreateClientRequest;
import bank.controller.request.UpdateClientAddBankRequest;
import bank.controller.response.BankResponse;
import bank.controller.response.ClientForBankResponse;
import bank.controller.response.ClientResponse;
import bank.domain.main.converter.BankConverter;
import bank.domain.main.converter.ClientConverter;
import bank.domain.model.Bank;
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
public class BankService {

    private final ClientRepository clientRepository;
    private final BankConverter bankConverter;
    private final ClientConverter clientConverter;
    private final BankRepository bankRepository;

    public ClientResponse createClient(CreateClientRequest createClientRequest) {
        var client = clientRepository.save(Client.builder().name(createClientRequest.getName()).build());
        return ClientResponse.builder()
                .name(client.getName())
                .build();
    }

    public List<BankResponse> findAllBanks() {
        return bankConverter.toListBankResponse(bankRepository.findAll());
    }

    public List<ClientForBankResponse> findClientsByNameBank(String name) {
        List<Client> clients = bankRepository.findClientsByName(name)
                .orElseThrow(() -> Message.BANK_WITH_NAME_NOT_FOUND.asBusinessException(name));
        return clientConverter.toListClientForBankResponse(clients);
    }

    public BankResponse findBankByName(String name) {
        Bank bank = bankRepository.findByName(name)
                .orElseThrow(() -> Message.BANK_WITH_NAME_NOT_FOUND.asBusinessException(name));
        return bankConverter.toBankResponse(bank);
    }

    public BankResponse createBank(CreateBankRequest createBankRequest) {
        return bankConverter.toBankResponse(bankRepository.save(Bank.builder()
                .name(createBankRequest.getName())
                .build()));
    }

    public String deleteBankByName(String name) {
        boolean exists = bankRepository.existsBankByName(name);
        if (exists) {
            clientRepository.deleteByName(name);
            return "Banco com o nome " + name + " foi deletado";
        }
        throw Message.BANK_WITH_NAME_NOT_FOUND.asBusinessException(name);
    }
}
