package nl.hakktastic.order.exception;

/**
 * Custom exception thrown when order is already created.
 */
public class OrderAlreadyExistsException extends Exception{

    public OrderAlreadyExistsException(String message){
        super(message);
    }
}
