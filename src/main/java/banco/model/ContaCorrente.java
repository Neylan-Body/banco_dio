package banco.model;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{
    public ContaCorrente(Banco banco, Cliente cliente){
        super(banco, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("------Extrato conta corrente--------");
        super.imprimirExtrato();
    }

    @Override
    BigDecimal aplicaTaxaDeTransacao(BigDecimal saldo) {
        return saldo.subtract(new BigDecimal("0.17"));
    }


}
