package bank.domain.model;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Slf4j
@Entity
public class CurrentAccount extends Account {

//    @Override
    public void printExtract() {
        log.info("------Extrato conta corrente--------");
//        super.printExtract();
    }

    BigDecimal applyTransactionFee(BigDecimal balance) {
        return balance.subtract(new BigDecimal("0.17"));
    }


}
