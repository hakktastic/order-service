package nl.hakktastic.order.controller;

import nl.hakktastic.order.exception.OrderNotCreatedException;
import nl.hakktastic.order.exception.OrderNotFoundException;
import nl.hakktastic.order.service.OrderService;
import nl.hakktastic.order.testdata.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerUnitTest {

    @InjectMocks
    OrderController controller;

    @Mock
    OrderService service;

    @Test
    void readOrders_noOrdersExist_returnOrderNotFoundException() {

        when(service.readOrders()).thenReturn(Optional.empty());

        assertThatExceptionOfType(OrderNotFoundException.class).isThrownBy(() -> controller.readOrders());
    }

    @Test
    void readOrders_ordersExist_returnOrders() throws OrderNotFoundException {

        var orderList = TestDataGenerator.getValidDummyOrderList();
        when(service.readOrders()).thenReturn(Optional.of(orderList));

        var result = controller.readOrders();

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(orderList);
    }

    @Test
    void createOrder_validOrderProvided_returnCreatedOrder() throws Exception {

        var order = TestDataGenerator.getMockOrder();

        when(service.createOrder(order)).thenReturn(Optional.of(order));

        var result = controller.createOrder(order);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void createOrder_orderAlreadyExists_throwOrderAlreadyExistsException() throws Exception {

        var order = TestDataGenerator.getMockOrder();

        when(service.createOrder(order)).thenReturn(Optional.empty());

        assertThatExceptionOfType(OrderNotCreatedException.class).isThrownBy(() -> controller.createOrder(order));
    }
}
