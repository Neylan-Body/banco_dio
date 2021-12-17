package banco.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String s) {
        super(s);
    }
}
