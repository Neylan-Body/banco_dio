package banco;

import banco.model.Banco;
import banco.model.Cliente;
import banco.model.Conta;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Cliente neylan = new Cliente("Neylan Oliveira");
        Cliente venilton = new Cliente("Venilton Falvo");

        Banco banco = new Banco("Qualquer Banco");

        Conta cc = new ContaCorrente(banco, neylan);
        Conta cp = new ContaPoupanca(banco, venilton);

        cc.depositar(new BigDecimal("500.50"));
        cc.depositar(new BigDecimal("50.50"));
        cc.sacar(new BigDecimal("10.50"));
        cp.depositar(new BigDecimal("1000"));
        cp.sacar(new BigDecimal("10.00"));
        cp.transferir(new BigDecimal("500.00"), cc);
        cc.imprimirExtrato();
        cp.imprimirExtrato();

        banco.imprimirClientes();

    }
}
