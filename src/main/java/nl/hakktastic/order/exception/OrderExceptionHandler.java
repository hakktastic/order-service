package nl.hakktastic.order.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Global exception handler for REST service.
 *
 */
@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public OrderError orderNotFoundException(OrderNotFoundException exception, WebRequest request){

        return OrderError.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public OrderError methodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){

        var errorList = exception.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return OrderError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .errorList(errorList)
                .build();
    }

    @ExceptionHandler({OrderNotCreatedException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public OrderError orderNotCreatedException(OrderNotCreatedException exception, WebRequest request){

        return OrderError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler({OrderAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public OrderError orderAlreadyExistsException(OrderAlreadyExistsException exception, WebRequest request){

        return OrderError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler({EmailDoesNotExistException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public OrderError emailDoesNotExistException(EmailDoesNotExistException exception, WebRequest request){

        return OrderError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler({ReqresApiException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public OrderError reqresApiException(ReqresApiException exception, WebRequest request){

        return OrderError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }
}
