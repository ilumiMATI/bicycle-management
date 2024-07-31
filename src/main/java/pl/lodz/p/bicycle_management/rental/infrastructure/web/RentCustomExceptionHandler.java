package pl.lodz.p.bicycle_management.rental.infrastructure.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.bicycle_management.rental.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.rental.domain.RentAlreadyExistsException;
import pl.lodz.p.bicycle_management.rental.domain.RentNotFoundException;

import java.io.IOException;

@ControllerAdvice
class RentCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RentNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleReservationNotFoundException(RentNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleReservationAlreadyExistsException(RentAlreadyExistsException ex) {
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