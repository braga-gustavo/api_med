/**
 * @author :Gustavo
 * Date :29/03/2023
 * Time :09:57
 * Project Name :med
 **/
package med.voll.med.infra.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404Exception() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400Exception(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleBusinessRuleException(ValidationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity handleException(ValidationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleDeniedAccessError() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");

    }

    private record DataValidationError(String field, String message) {

        public DataValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());

        }
    }

}
