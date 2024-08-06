package pl.lodz.p.bicycle_management.payment.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.bicycle_management.payment.command.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.payment.command.domain.PaymentInsufficientFundsException;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWalletAlreadyExistsException;

import java.io.IOException;

@ControllerAdvice
public class PaymentCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserWalletAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleMethodNotAllowedException(UserWalletAlreadyExistsException ex) {
        return buildResponse(ex,  HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PaymentInsufficientFundsException.class)
    public final ResponseEntity<ErrorResponse> handleMethodNotAllowedException(PaymentInsufficientFundsException ex) {
        return buildResponse(ex,  HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public final ResponseEntity<ErrorResponse> handleMethodNotAllowedException(MethodNotAllowedException ex) {
        return buildResponse(ex,  HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IOException.class)
    public final ResponseEntity<ErrorResponse> handleCommandNotSupportedException(IOException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }

    private <E extends RuntimeException> ResponseEntity<ErrorResponse> buildResponse(E exception, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(exception.getMessage()));
    }
}
