/**
 * @author :Gustavo
 * Date :29/03/2023
 * Time :09:57
 * Project Name :med
 **/
package med.voll.med.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    private record DataValidationError(String field, String message) {

        public DataValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());

        }
    }

}
