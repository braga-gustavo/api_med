package med.voll.med.infra.exception;

public class ValidationException extends RuntimeException
{

    public ValidationException(String message) {
        super(message);
    }
}
