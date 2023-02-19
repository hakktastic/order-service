package nl.hakktastic.order.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.entity.Order;
import nl.hakktastic.order.exception.OrderAlreadyExistsException;
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

            log.debug("Order Service - reading all orders: no orders found in repository");

            return Optional.empty();
        }

        return Optional.of(orderList);
    }

    public Optional<Order> createOrder(Order order) throws OrderAlreadyExistsException {

        log.debug("Order Service - create order");

        var existingOrder = repository.findByProductIDAndEmail(order.getProductID(), order.getEmail());

        if(existingOrder != null){

            String message = "order with ID="+order.getOrderID()+
                    " already exists for product="+ order.getProductID() +
                    ", email=" + order.getEmail();

            log.debug("Order Service - create order: {}", message);

            throw new OrderAlreadyExistsException(message);
        }

        // TODO validate that email exists in reqres

        return Optional.of(repository.save(order));
    }
}