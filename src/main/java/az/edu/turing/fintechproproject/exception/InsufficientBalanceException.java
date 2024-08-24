package az.edu.turing.fintechproproject.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}// Thrown when there isn't enough balance in the account for a transfer.