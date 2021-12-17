package banco.model;

import banco.exception.SaldoInsuficienteException;
import banco.exception.ValorIncorretoException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static banco.BancoUtils.dataEHoraAtual;

@Slf4j
@Getter
public abstract class Conta {

    private static int seq = 1;

    private final int agencia;
    private final int numeroConta;
    private BigDecimal saldo;
    private List<String> operacoes;
    private final Cliente cliente;
    private final Banco banco;

    protected Conta(Banco banco, Cliente cliente) {
        this.agencia = 1;
        this.numeroConta = seq++;
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
        this.operacoes.add(dataEHoraAtual() + " - Foi sacado R$ " + valor);
        saldo = saldo.subtract(valor);
        return valor;
    }

    public void depositar(BigDecimal valor) {
        verificaValorNegativo(valor);

        this.operacoes.add(dataEHoraAtual() + " - Foi depositado R$ " + valor);
        saldo = saldo.add(valor);
    }

    protected void receberTransferencia(BigDecimal valor, Conta origem) {
        verificaValorNegativo(valor);
        this.operacoes.add(dataEHoraAtual() + " - Foi recebido de transferência R$ " + valor + ", " +
                "da " + stringComContaEAgencia(origem.getNumeroConta(), origem.getAgencia()));
        saldo = saldo.add(valor);
    }

    public void transferir(BigDecimal valor, Conta destino) {
        verificaValorNegativo(valor);
        verificaSaldoInsuficiente(valor);
        saldo = aplicaTaxaDeTransacao(saldo);
        this.operacoes.add(dataEHoraAtual() + " - Foi transferido R$ " + valor + ", " +
                "para " + stringComContaEAgencia(destino.getNumeroConta(), destino.getAgencia()));
        saldo = saldo.subtract(valor);
        destino.receberTransferencia(valor, this);
    }

    public void imprimirExtrato() {
        log.info("Titular: " + this.cliente.getNome());
        log.info(stringComContaEAgencia(this.getNumeroConta(), this.getAgencia()));
        this.operacoes.forEach(log::info);
        log.info(dataEHoraAtual() + " - Saldo: R$ " + this.saldo + "\n");
    }

    private String stringComContaEAgencia(int conta, int agencia) {
        return "Conta: " + conta + ", Agencia: " + agencia;
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
