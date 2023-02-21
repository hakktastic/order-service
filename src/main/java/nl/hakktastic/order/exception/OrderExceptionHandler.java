package nl.hakktastic.order.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @ApiResponse(responseCode = "404", description = "order not found")
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

    @ApiResponse(responseCode = "400", description = "validation error")
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

    @ApiResponse(responseCode = "500", description = "unexpected error occurred while creating an order")
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

    @ApiResponse(responseCode = "400", description = "order already exists")
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

    @ApiResponse(responseCode = "400", description = "email does not exist")
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

    @ApiResponse(responseCode = "500", description = "unexpected error occurred while rerieving users from Reqres API")
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
