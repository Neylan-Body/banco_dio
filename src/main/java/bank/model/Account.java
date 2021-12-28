package bank.model;

import bank.exception.InsufficientFundsException;
import bank.exception.IncorrectValueException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static bank.BankUtils.currentDateTime;

@Slf4j
@Getter
public abstract class Account {

    private static int seq = 1;

    private final int agency;
    private final int accountNumber;
    private BigDecimal balance;
    private List<String> operations;
    private final Client client;
    private final Bank bank;

    protected Account(Bank bank, Client client) {
        this.agency = 1;
        this.accountNumber = seq++;
        this.balance = BigDecimal.ZERO;
        this.operations = new ArrayList<>();
        this.client = client;
        bank.setClient(client);
        this.bank = bank;
    }

    public BigDecimal withdraw(BigDecimal value) {
        checkNegativeValue(value);
        checkInsufficientFunds(value);
        balance = applyTransactionFee(balance);
        this.operations.add(currentDateTime() + " - Foi sacado R$ " + value);
        balance = balance.subtract(value);
        return value;
    }

    public void deposit(BigDecimal value) {
        checkNegativeValue(value);

        this.operations.add(currentDateTime() + " - Foi depositado R$ " + value);
        balance = balance.add(value);
    }

    protected void receiveTransfer(BigDecimal value, Account origin) {
        checkNegativeValue(value);
        this.operations.add(currentDateTime() + " - Foi recebido de transferência R$ " + value + ", " +
                "da " + stringWithAccountNumberAndAgency(origin.getAccountNumber(), origin.getAgency()));
        balance = balance.add(value);
    }

    public void transfer(BigDecimal value, Account destination) {
        checkNegativeValue(value);
        checkInsufficientFunds(value);
        balance = applyTransactionFee(balance);
        this.operations.add(currentDateTime() + " - Foi transferido R$ " + value + ", " +
                "para " + stringWithAccountNumberAndAgency(destination.getAccountNumber(), destination.getAgency()));
        balance = balance.subtract(value);
        destination.receiveTransfer(value, this);
    }

    public void printExtract() {
        log.info("Titular: " + this.client.getName());
        log.info(stringWithAccountNumberAndAgency(this.getAccountNumber(), this.getAgency()));
        this.operations.forEach(log::info);
        log.info(currentDateTime() + " - Saldo: R$ " + this.balance + "\n");
    }

    private String stringWithAccountNumberAndAgency(int account, int agency) {
        return "Conta: " + account + ", Agencia: " + agency;
    }

    private void checkNegativeValue(BigDecimal value) {
        if (value.doubleValue() <= 0) {
            throw new IncorrectValueException("O valor passado foi menor ou igual a zero");
        }
    }

    private void checkInsufficientFunds(BigDecimal value) {
        if (value.compareTo(this.balance) > 0) {
            throw new InsufficientFundsException("O valor a ser sacado foi maior " +
                    "que o valor presente na conta");
        }
    }

    abstract BigDecimal applyTransactionFee(BigDecimal balance);
}
