package banco.model;

import banco.exception.SaldoInsuficienteException;
import banco.exception.ValorIncorretoException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Conta {

    private static int seq = 1;

    private final int agencia;
    private final int numero;
    private BigDecimal saldo;
    private List<String> operacoes;
    private final Cliente cliente;
    private final Banco banco;

    protected Conta(Banco banco, Cliente cliente) {
        this.agencia = 1;
        this.numero = seq++;
        this.saldo = BigDecimal.ZERO;
        this.operacoes = new ArrayList<>();
        this.cliente = cliente;
        banco.setCliente(cliente);
        this.banco = banco;
    }

    public BigDecimal sacar(BigDecimal valor) {
        verificaValorNegativo(valor);
        verificaSaldoInsuficiente(valor);
        saldo = aplicaTaxaDeTransacao(saldo);
        this.operacoes.add("Foi sacado R$ " + valor);
        saldo = saldo.subtract(valor);
        return valor;
    }

    public void depositar(BigDecimal valor) {
        verificaValorNegativo(valor);
        this.operacoes.add("Foi depositado R$ " + valor);
        saldo = saldo.add(valor);
    }

    protected void receberTransferencia(BigDecimal valor, Conta origem) {
        verificaValorNegativo(valor);
        this.operacoes.add("Foi recebido de transferência R$ " + valor + ", " +
                "da Conta: " + origem.getNumero() + ", Agencia: " + origem.getAgencia());
        saldo = saldo.add(valor);
    }

    public void transferir(BigDecimal valor, Conta destino) {
        verificaValorNegativo(valor);
        verificaSaldoInsuficiente(valor);
        saldo = aplicaTaxaDeTransacao(saldo);
        this.operacoes.add("Foi transferido R$ " + valor + ", " +
                "para Conta: " + destino.getNumero() + ", Agencia: " + destino.getAgencia());
        saldo = saldo.subtract(valor);
        destino.receberTransferencia(valor, this);
    }

    public void imprimirExtrato() {
        System.out.println("Titular: "+ this.cliente.getNome());
        System.out.println("Conta: " + this.numero + ", Agencia: " + this.agencia);
        this.operacoes.forEach(System.out::println);
        System.out.println("Saldo: R$ " + this.saldo+"\n");
    }

    private void verificaValorNegativo(BigDecimal valor) {
        if (valor.doubleValue() <= 0) {
            throw new ValorIncorretoException("O valor passado foi menor ou igual a zero");
        }
    }

    private void verificaSaldoInsuficiente(BigDecimal valor) {
        if (valor.compareTo(this.saldo) > 0) {
            throw new SaldoInsuficienteException("O valor a ser sacado foi maior " +
                    "que o valor presente na conta");
        }
    }

    abstract BigDecimal aplicaTaxaDeTransacao(BigDecimal saldo);
}
