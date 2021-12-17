package banco.model;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{

    public ContaPoupanca(Banco banco, Cliente cliente){
        super(banco, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("------Extrato conta poupança--------");
        super.imprimirExtrato();
    }

    @Override
    BigDecimal aplicaTaxaDeTransacao(BigDecimal saldo) {
        return saldo.subtract(new BigDecimal("0.55"));
    }
}
