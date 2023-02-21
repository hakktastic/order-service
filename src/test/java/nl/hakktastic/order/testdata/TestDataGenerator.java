package nl.hakktastic.order.testdata;

import nl.hakktastic.order.client.response.Data;
import nl.hakktastic.order.client.response.Response;
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

    /**
     * Get mock order.
     *
     * @return a mock order
     */
    public static Order getMockOrder(){

        return Order
                .builder()
                .email("test.user@reqres.in")
                .firstName("test")
                .lastName("user")
                .productID(1)
                .build();
    }

    /**
     * Get a mock response.
     *
     * @return a mock response
     */
    public static Response getMockResponse(){

        var mockData = Data
                .builder()
                .id(1)
                .email("test.user@reqres.in")
                .first_name("test")
                .last_name("user")
                .avatar("https://reqres.in/img/faces/x-image.jpg")
                .build();

        return Response
                .builder()
                .page(1)
                .per_page(1)
                .total(1)
                .total_pages(1)
                .data(List.of(mockData))
                .build();
    }

}
