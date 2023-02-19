package nl.hakktastic.order.exception;

/**
 * Custom exception thrown when order is not created.
 *
 */
public class OrderNotCreatedException extends  Exception{

    public OrderNotCreatedException(String message){
        super(message);
    }
}
