package bank.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String s) {
        super(s);
    }
}
