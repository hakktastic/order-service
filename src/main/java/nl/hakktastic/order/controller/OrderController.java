package nl.hakktastic.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.entity.Order;
import nl.hakktastic.order.exception.*;
import nl.hakktastic.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Order Controller.
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class OrderController {

    private final OrderService service;

    /**
     * Constructor.
     *
     * @param orderService provide service object with constructor injection.
     */
    public OrderController(OrderService orderService){

        this.service = orderService;
    }

    /**
     * Read orders.
     *
     * @return Returns all orders if entities exist
     * @throws OrderNotFoundException Throws a custom exception with reponse code 404 if no entities are found
     */
    @Operation(summary = "read all orders")
    @ApiResponse(responseCode = "200", description = "orders found in repository")
    @GetMapping(value = "/orders", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> readOrders() throws OrderNotFoundException {

        log.debug("Order Controller - read all orders");

        return new ResponseEntity<>(service
                .readOrders()
                .orElseThrow(() -> new OrderNotFoundException("No orders found")),
                HttpStatus.OK);
    }

    @Operation(summary = "create an order")
    @ApiResponse(responseCode = "201", description = "order created successfully")
    @PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) throws OrderNotCreatedException, OrderAlreadyExistsException, EmailDoesNotExistException, ReqresApiException {

        log.debug("Order Controller - create Order ='{}'", order);

        return new ResponseEntity<>(service
                .createOrder(order)
                .orElseThrow(() -> new OrderNotCreatedException("An unexpected error occured while creating order")), HttpStatus.CREATED);
    }
}
