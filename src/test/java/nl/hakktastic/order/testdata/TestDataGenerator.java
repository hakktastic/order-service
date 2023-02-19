package nl.hakktastic.order.testdata;

import nl.hakktastic.order.entity.Order;

import java.util.List;

/**
 * Class for generating test data.
 */
public class TestDataGenerator {

    /**
     * Get a list with orders.
     *
     * @return Returns a list with orders
     */
    public static List<Order> getValidDummyOrderList(){

        var order1 = Order.builder()
                .orderID(1)
                .email("test@google.com")
                .firstName("testname")
                .lastName("testLast")
                .productID(1)
                .build();
        var order2 = Order.builder()
                .orderID(2)
                .email("test@google.com")
                .firstName("testname")
                .lastName("testLast")
                .productID(2)
                .build();
        var order3 = Order.builder()
                .orderID(2)
                .email("test@google.com")
                .firstName("testname")
                .lastName("testLast")
                .productID(2)
                .build();

        return List.of(order1,order2, order3);
    }
}
