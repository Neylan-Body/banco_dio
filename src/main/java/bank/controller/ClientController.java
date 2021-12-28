package bank.controller;

import bank.controller.request.CreateBankRequest;
import bank.controller.request.UpdateClientAddBankRequest;
import bank.controller.response.ClientResponse;
import bank.domain.main.converter.ClientConverter;
import bank.domain.model.Client;
import bank.repository.BankRepository;
import bank.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/client")
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ClientConverter clientConverter;

    private final ClientRepository clientRepository;

    private final BankRepository bankRepository;

    @GetMapping("/")
    public List<ClientResponse> getClients(){
        return clientConverter.toListClientResponse(clientRepository.findAll());
    }

    @GetMapping("/{name}")
    public ClientResponse getClientByName(@PathVariable("name") String name){
        return clientConverter.toClientResponse(clientRepository.findByName(name));
    }

    @PostMapping("/")
    public void createBank(@RequestBody CreateBankRequest createBankRequest){
        clientRepository.save(Client.builder().name(createBankRequest.getName()).build());
    }

    @DeleteMapping("/{name}")
    @Transactional
    public void deleteClientByName(@PathVariable("name") String name){
        clientRepository.deleteByName(name);
    }

    @PatchMapping("/{name}")
    public void addBank(@PathVariable("name") String name, @RequestBody UpdateClientAddBankRequest updateClientAddBank){
        var bank = bankRepository.findByName(updateClientAddBank.getNameBank());
        var client = clientRepository.findByName(name);
        client.addBank(bank);
        clientRepository.save(client);
    }
}