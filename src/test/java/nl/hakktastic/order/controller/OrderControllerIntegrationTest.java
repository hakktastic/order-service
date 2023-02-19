package nl.hakktastic.order.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerIntegrationTest {

    private static final String URL_TEMPLATE_ORDERS = "/api/orders";

    @Autowired
    private MockMvc mockMvc;


    @Test
    void readOrders_ordersExist_returnListWithOrders() throws Exception {

        mockMvc.perform(get(URL_TEMPLATE_ORDERS).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
