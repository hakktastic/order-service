package nl.hakktastic.order.exception;

/**
 * Custom exception thrown when email does not exist.
 */
public class EmailDoesNotExistException extends Exception {

    public EmailDoesNotExistException(String message){
        super(message);
    }
}
