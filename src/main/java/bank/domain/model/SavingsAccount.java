package bank.domain.model;

import bank.domain.model.enumerator.AccountTypeEnum;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Slf4j
@Entity
public class SavingsAccount extends Account {

    public SavingsAccount(Bank bank, Client client){
        super(bank, client, AccountTypeEnum.SAVINGS);
    }

//    @Override
    public void printExtract() {
        log.info("------Extrato conta poupança--------");
//        super.printExtract();
    }

    @Override
    BigDecimal applyTransactionFee(BigDecimal balance) {
        return balance.subtract(new BigDecimal("0.55"));
    }
}
