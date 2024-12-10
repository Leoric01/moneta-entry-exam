package leoric.monetaentrytrial.handler;

import jakarta.persistence.EntityNotFoundException;
import leoric.monetaentrytrial.dtos.responses.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Result<Void>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.warn("Invalid URL accessed: {}", ex.getMessage());
        return ResponseEntity
                .status(NOT_FOUND)
                .body(Result.failure(NOT_FOUND.value(), "Invalid URL. Please check your request. For help, refer to our documentation: [link]"));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Result<Void>> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.warn("Entity not found: {}", ex.getMessage());
        return ResponseEntity
                .status(NOT_FOUND)
                .body(Result.failure(NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handleGenericException(Exception exp) {
        log.error("Unexpected error occurred: {}", exp.getMessage(), exp);
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(Result.failure(INTERNAL_SERVER_ERROR.value(), "Internal error, please contact the admin"));
    }

    @ExceptionHandler(CustomProblemException.class)
    public ResponseEntity<Result<Void>> handleProblemBasedOnBusinessCasesException(CustomProblemException ex) {
        log.warn("Whatever the problem : {}", ex.getMessage());
        return ResponseEntity
                .status(FORBIDDEN)
                .body(Result.failure(FORBIDDEN.value(), ex.getMessage()));
    }
}