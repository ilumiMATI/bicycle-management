package pl.lodz.p.bicycle_management.bicycle.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleAlreadyExistsException;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNotFoundException;

import java.io.IOException;

public class BicycleCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BicycleNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleBicycleNotFoundException(BicycleNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BicycleAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleBicycleAlreadyLockedException(BicycleAlreadyExistsException ex) {
        return buildResponse(ex,  HttpStatus.CONFLICT);
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
