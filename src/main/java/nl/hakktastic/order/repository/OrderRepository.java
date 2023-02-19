package nl.hakktastic.order.repository;

import nl.hakktastic.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Order Repository.
 *
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {


    Order findByProductIDAndEmail(int productID, String email);
}
