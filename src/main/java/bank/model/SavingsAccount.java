package bank.model;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class SavingsAccount extends Account {

    public SavingsAccount(Bank bank, Client client){
        super(bank, client);
    }

    @Override
    public void printExtract() {
        log.info("------Extrato conta poupança--------");
        super.printExtract();
    }

    @Override
    BigDecimal applyTransactionFee(BigDecimal balance) {
        return balance.subtract(new BigDecimal("0.55"));
    }
}
