package az.edu.turing.fintechproproject.exception;

public class UserAlreadyExistsException extends  RuntimeException{ //Thrown when a user tries to register with a PIN that is already registered.
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
