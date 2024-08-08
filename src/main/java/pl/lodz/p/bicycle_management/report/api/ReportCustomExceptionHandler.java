package pl.lodz.p.bicycle_management.report.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.bicycle_management.report.domain.RentalPaymentReportAlreadyExistsException;
import pl.lodz.p.bicycle_management.report.domain.RentalPaymentReportNotFoundException;
import pl.lodz.p.bicycle_management.report.domain.RentalReportAlreadyExistsException;
import pl.lodz.p.bicycle_management.report.domain.RentalReportNotFoundException;

import java.io.IOException;

@ControllerAdvice
class ReportCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RentalPaymentReportAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(RentalPaymentReportAlreadyExistsException ex) {
        return buildResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RentalPaymentReportNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(RentalPaymentReportNotFoundException ex) {
        return buildResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalReportAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(RentalReportAlreadyExistsException ex) {
        return buildResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RentalReportNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(RentalReportNotFoundException ex) {
        return buildResponse(ex, HttpStatus.NOT_FOUND);
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