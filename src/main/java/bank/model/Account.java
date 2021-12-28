package bank.model;

import bank.model.enumerator.AccountTypeEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Entity
public abstract class Account implements Serializable {

    private static final long serialVersionUID = -1888303910055235185L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private final Integer agency;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountNumber;

    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operation> operations;

    @OneToOne(cascade = CascadeType.ALL)
    private final Client client;

    @OneToOne(cascade = CascadeType.ALL)
    private final Bank bank;

    private final AccountTypeEnum accountTypeEnum;

    protected Account(Bank bank, Client client, AccountTypeEnum accountTypeEnum) {
        this.agency = 1;
        this.balance = BigDecimal.ZERO;
        this.operations = new ArrayList<>();
        this.client = client;
        bank.addClient(client);
        this.bank = bank;
        this.accountTypeEnum = accountTypeEnum;
    }

//    public BigDecimal withdraw(BigDecimal value) {
//        checkNegativeValue(value);
//        checkInsufficientFunds(value);
//        balance = applyTransactionFee(balance);
//        this.operations.add(currentDateTime() + " - Foi sacado R$ " + value);
//        balance = balance.subtract(value);
//        return value;
//    }
//
//    public void deposit(BigDecimal value) {
//        checkNegativeValue(value);
//
//        this.operations.add(currentDateTime() + " - Foi depositado R$ " + value);
//        balance = balance.add(value);
//    }
//
//    protected void receiveTransfer(BigDecimal value, Account origin) {
//        checkNegativeValue(value);
//        this.operations.add(currentDateTime() + " - Foi recebido de transferência R$ " + value + ", " +
//                "da " + stringWithAccountNumberAndAgency(origin.getAccountNumber(), origin.getAgency()));
//        balance = balance.add(value);
//    }
//
//    public void transfer(BigDecimal value, Account destination) {
//        checkNegativeValue(value);
//        checkInsufficientFunds(value);
//        balance = applyTransactionFee(balance);
//        this.operations.add(currentDateTime() + " - Foi transferido R$ " + value + ", " +
//                "para " + stringWithAccountNumberAndAgency(destination.getAccountNumber(), destination.getAgency()));
//        balance = balance.subtract(value);
//        destination.receiveTransfer(value, this);
//    }
//
//    public void printExtract() {
//        log.info("Titular: " + this.client.getName());
//        log.info(stringWithAccountNumberAndAgency(this.getAccountNumber(), this.getAgency()));
//        this.operations.forEach(log::info);
//        log.info(currentDateTime() + " - Saldo: R$ " + this.balance + "\n");
//    }
//
//    private String stringWithAccountNumberAndAgency(int account, int agency) {
//        return "Conta: " + account + ", Agencia: " + agency;
//    }
//
//    private void checkNegativeValue(BigDecimal value) {
//        if (value.doubleValue() <= 0) {
//            throw new IncorrectValueException("O valor passado foi menor ou igual a zero");
//        }
//    }
//
//    private void checkInsufficientFunds(BigDecimal value) {
//        if (value.compareTo(this.balance) > 0) {
//            throw new InsufficientFundsException("O valor a ser sacado foi maior " +
//                    "que o valor presente na conta");
//        }
//    }

    abstract BigDecimal applyTransactionFee(BigDecimal balance);
}
