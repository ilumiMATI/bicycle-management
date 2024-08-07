package pl.lodz.p.bicycle_management.rental.api;

import pl.lodz.p.bicycle_management.rental.command.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.rental.command.domain.NoMinimalFundsException;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsAlreadyExistsException;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsNotFoundException;
import pl.lodz.p.bicycle_management.rental.query.facade.UserRentalsDtoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
class RentalCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoMinimalFundsException.class)
    public final ResponseEntity<ErrorResponse> handleUserRentalsNotFoundException(NoMinimalFundsException ex) {
        return buildResponse(ex,  HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(UserRentalsNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserRentalsNotFoundException(UserRentalsNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRentalsDtoNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserRentalsDtoNotFoundException(UserRentalsDtoNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRentalsAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleUserRentalsAlreadyExistsException(UserRentalsAlreadyExistsException ex) {
        return buildResponse(ex,  HttpStatus.CONFLICT);
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