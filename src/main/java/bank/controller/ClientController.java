package bank.controller;

import bank.controller.request.CreateClientRequest;
import bank.controller.request.UpdateClientAddBankRequest;
import bank.controller.response.ClientResponse;
import bank.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client")
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<ClientResponse>> getClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllClients());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ClientResponse>> getClientByName(@PathVariable("name") String name){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientsByName(name));
    }

    @PostMapping("/")
    public ResponseEntity<ClientResponse> createClient(@RequestBody CreateClientRequest createClientRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(createClientRequest));
    }

    @DeleteMapping("/{name}")
    @Transactional
    public ResponseEntity<Void> deleteClientByName(@PathVariable("name") String name){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> addBank(@PathVariable("clientId") Integer clientId, @RequestBody UpdateClientAddBankRequest updateClientAddBankRequest){
        clientService.addBank(clientId, updateClientAddBankRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}