package az.edu.turing.fintechproproject.exception;

public class CurrencyRateNotFoundException extends RuntimeException {
    public CurrencyRateNotFoundException(String message) {
        super(message);
    }
}//Thrown when the requested currency rate is not found.