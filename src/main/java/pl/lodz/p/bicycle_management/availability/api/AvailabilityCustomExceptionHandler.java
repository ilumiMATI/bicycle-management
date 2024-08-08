package pl.lodz.p.bicycle_management.availability.api;

import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAlreadyExistsException;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAlreadyLockedException;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleNotFoundException;
import pl.lodz.p.bicycle_management.availability.command.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.availability.query.facade.BicycleAvailabilityDtoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class AvailabilityCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BicycleNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleBicycleNotFoundException(BicycleNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BicycleAvailabilityDtoNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleBicycleAvailabilityDtoNotFoundException(BicycleAvailabilityDtoNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BicycleAlreadyLockedException.class)
    public final ResponseEntity<ErrorResponse> handleBicycleAlreadyLockedException(BicycleAlreadyLockedException ex) {
        return buildResponse(ex,  HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BicycleAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleBicycleAlreadyExistsException(BicycleAlreadyExistsException ex) {
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