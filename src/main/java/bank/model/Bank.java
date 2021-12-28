package bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static bank.BankUtils.currentDateTime;


@Slf4j
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "banks", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Client> clients = new ArrayList<>();

    public Bank(String name){
        this.name = name;
    }

    public void addClient(Client client){
        this.clients.add(client);
    }

    public void printClients(){
        log.info(currentDateTime() + " - Clientes do Banco: "+ this.name);
        clients.forEach(client -> log.info(client.getName()));
    }
}
