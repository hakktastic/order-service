package nl.hakktastic.order.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.entity.Order;
import nl.hakktastic.order.exception.EmailDoesNotExistException;
import nl.hakktastic.order.exception.OrderAlreadyExistsException;
import nl.hakktastic.order.exception.ReqresApiException;
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
    private final ReqresUserService reqresUserService;

    /**
     * Constructor.
     *
     * @param orderRepository provide repository object with constructor injection
     */
    public OrderService(OrderRepository orderRepository, ReqresUserService reqresService){

        this.repository = orderRepository;
        this.reqresUserService = reqresService;
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

    /**
     * Create Order if product for user is not yet ordered and if email address exists.
     *
     * @param order the order
     * @return Returns the {@link  Order}
     * @throws OrderAlreadyExistsException if product is already ordered by user
     * @throws EmailDoesNotExistException if email address does not exist in Reqres API
     * @throws ReqresApiException if an error occurs on Reqres API
     */
    public Optional<Order> createOrder(Order order) throws OrderAlreadyExistsException, EmailDoesNotExistException, ReqresApiException {

        log.debug("Order Service - create order");

        var existingOrder = repository.findByProductIDAndEmail(order.getProductID(), order.getEmail());

        if(existingOrder != null){

            String message = "order with ID="+order.getOrderID()+
                    " already exists for product="+ order.getProductID() +
                    ", email=" + order.getEmail();

            log.info("Order Service - create order: {}", message);
            throw new OrderAlreadyExistsException(message);
        }

        if(!reqresUserService.emailExists(order.getEmail())){

            var message = "Email " + order.getEmail() + " does not exist in Reqres API";

            log.info("Order Service - create order: {}", message);
            throw new EmailDoesNotExistException(message);
        }

        return Optional.of(repository.save(order));
    }
}