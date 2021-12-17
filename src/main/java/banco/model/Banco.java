package banco.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static banco.BancoUtils.dataEHoraAtual;

@Slf4j
@Getter
public class Banco {
    private String nome;
    private List<Cliente> clientes;

    public Banco(String nome){
        this.nome = nome;
        this.clientes = new ArrayList<>();
    }

    public void setCliente(Cliente cliente){
        this.clientes.add(cliente);
    }

    public void imprimirClientes(){
        log.info(dataEHoraAtual() + " - Clientes do Banco: "+ this.nome);
        clientes.forEach(cliente -> log.info(cliente.getNome()));
    }
}
