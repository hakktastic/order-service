package nl.hakktastic.order.exception;

/**
 * Custom exception thrown when error occurs in Reqres API.
 */
public class ReqresApiException extends Exception{

    public ReqresApiException(String message){
        super(message);
    }
}
