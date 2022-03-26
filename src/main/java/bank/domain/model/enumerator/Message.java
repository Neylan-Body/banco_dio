package bank.domain.model.enumerator;

import bank.configuration.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Message {

    ACCOUNT_NOT_FOUND("Conta de numero=%s e agencia=%s não foi encontrada", HttpStatus.BAD_REQUEST),
    BANK_WITH_NAME_NOT_FOUND("Banco de nome=%s e não foi encontrado", HttpStatus.BAD_REQUEST),
    CLIENT_WITH_NAME_NOT_FOUND("Cliente de nome=%s e não foi encontrado", HttpStatus.BAD_REQUEST),
    CLIENT_WITH_ID_NOT_FOUND("Cliente de id=%s e não foi encontrado", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_FUNDS("Saldo insuficiente", HttpStatus.BAD_REQUEST);


    private final String message;
    private final HttpStatus statusCode;

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatusCode()).message(this.getMessage()).build();
    }

    public BusinessException asBusinessException(String... strings) {
        return BusinessException.builder().httpStatusCode(this.getStatusCode()).message(this.formatMessage(strings)).build();
    }

    public BusinessException asBusinessException(Object object, String... strings) {
        return BusinessException.builder().httpStatusCode(this.getStatusCode()).message(this.formatMessage(strings)).build();
    }

    public String formatMessage(String... strings) {
        return String.format(this.getMessage(), strings);
    }
}
