package bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static bank.BankUtils.currentDateTime;


@Slf4j
@Getter
@Entity
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private List<Client> clients;

    public Bank(String name){
        this.name = name;
        this.clients = new ArrayList<>();
    }

    public void setClient(Client client){
        this.clients.add(client);
    }

    public void printClients(){
        log.info(currentDateTime() + " - Clientes do Banco: "+ this.name);
        clients.forEach(client -> log.info(client.getName()));
    }
}
