package bank.model;

import bank.model.enumerator.AccountTypeEnum;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Slf4j
@Entity
public class CurrentAccount extends Account {

    public CurrentAccount(Bank bank, Client client){
        super(bank, client, AccountTypeEnum.CURRENT);
    }

//    @Override
    public void printExtract() {
        log.info("------Extrato conta corrente--------");
//        super.printExtract();
    }

    @Override
    BigDecimal applyTransactionFee(BigDecimal balance) {
        return balance.subtract(new BigDecimal("0.17"));
    }


}
