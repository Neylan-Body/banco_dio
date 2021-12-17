package banco.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Clientes do Banco: "+ this.nome);
        clientes.forEach(cliente -> System.out.println(cliente.getNome()));
    }
}
