package nl.hakktastic.order.exception;

/**
 * Custom exception thrown when no order is found.
 */
public class OrderNotFoundException extends Exception{

    public OrderNotFoundException(String message){
        super(message);
    }
}
