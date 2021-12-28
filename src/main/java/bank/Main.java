package bank;

import bank.model.*;
import bank.model.Bank;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Client neylan = new Client("Neylan Oliveira");
        Client venilton = new Client("Venilton Falvo");

        Bank bank = new Bank("Qualquer Banco");

        Account cc = new CurrentAccount(bank, neylan);
        Account cp = new SavingsAccount(bank, venilton);

        cc.deposit(new BigDecimal("500.50"));
        cc.deposit(new BigDecimal("50.50"));
        cc.withdraw(new BigDecimal("10.50"));
        cp.deposit(new BigDecimal("1000"));
        cp.withdraw(new BigDecimal("10.00"));
        cp.transfer(new BigDecimal("500.00"), cc);
        cc.printExtract();
        cp.printExtract();

        bank.printClients();

    }
}
