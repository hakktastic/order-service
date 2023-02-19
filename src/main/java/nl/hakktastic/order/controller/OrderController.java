package nl.hakktastic.order.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.entity.Order;
import nl.hakktastic.order.exception.OrderNotFoundException;
import nl.hakktastic.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/orders", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> readOrders() throws OrderNotFoundException {

        log.debug("Order Controller - read all orders");

        return new ResponseEntity<>(service
                .readOrders()
                .orElseThrow(() -> new OrderNotFoundException("No orders found")),
                HttpStatus.OK);
    }
}
