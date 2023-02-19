package nl.hakktastic.order.service;


import nl.hakktastic.order.repository.OrderRepository;
import nl.hakktastic.order.testdata.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {

    @InjectMocks
    OrderService service;

    @Mock
    OrderRepository repository;

    @Test
    void readOrders_NoOrdersExist_ReturnEmptyList(){

        when(repository.findAll()).thenReturn(List.of());
        var result = service.readOrders();

       assertThat(result).isEmpty();
    }

    @Test
    void readOrders_OrdersExist_ReturnListWithOrders(){

        var orderList = TestDataGenerator.getValidDummyOrderList();
        when(repository.findAll()).thenReturn(orderList);
        var result = service.readOrders();

        assertThat(result).contains(orderList);
    }
}
