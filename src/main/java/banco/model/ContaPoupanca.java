package banco.model;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class ContaPoupanca extends Conta{

    public ContaPoupanca(Banco banco, Cliente cliente){
        super(banco, cliente);
    }

    @Override
    public void imprimirExtrato() {
        log.info("------Extrato conta poupança--------");
        super.imprimirExtrato();
    }

    @Override
    BigDecimal aplicaTaxaDeTransacao(BigDecimal saldo) {
        return saldo.subtract(new BigDecimal("0.55"));
    }
}
