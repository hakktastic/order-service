package nl.hakktastic.order.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.entity.Order;
import nl.hakktastic.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Order Service.
 *
 */
@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;

    /**
     * Constructor.
     *
     * @param orderRepository provide repository object with constructor injection
     */
    public OrderService(OrderRepository orderRepository){

        this.repository = orderRepository;
    }

    /**
     * Read all orders.
     *
     * @return Returns an {@link Optional with a {@link List}} of {@link Order entities.}
     */
    public Optional<List<Order>> readOrders(){

        log.debug("Order Service - reading all orders");

        var orderList = repository.findAll();

        if(orderList.isEmpty()){

            log.debug("Order Service - reading all orders no orders found in repository");

            return Optional.empty();
        }

        return Optional.of(orderList);
    }
}
