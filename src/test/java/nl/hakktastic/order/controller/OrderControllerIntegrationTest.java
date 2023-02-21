package nl.hakktastic.order.controller;

import com.google.gson.Gson;
import nl.hakktastic.order.client.response.Response;
import nl.hakktastic.order.entity.Order;
import nl.hakktastic.order.testdata.TestDataGenerator;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerIntegrationTest {

    private static final String URL_TEMPLATE_ORDERS = "/api/orders";

    @Autowired
    private MockMvc mockMvc;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {

        mockWebServer = new MockWebServer();
        mockWebServer.start(9092);
    }

    @AfterAll
    static void tearDown() throws IOException {

        mockWebServer.shutdown();
    }


    @Test
    void readOrders_ordersExist_returnListWithOrders() throws Exception {

        mockMvc.perform(get(URL_TEMPLATE_ORDERS).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder_validOrderProvided_returnCreatedOrder() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setBody(new Gson().toJson(TestDataGenerator.getMockResponse()))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value()));

        var order =  new Gson()
                .toJson(Order.builder()
                        .productID(1000)
                        .firstName("test")
                        .lastName("user")
                        .email("test.user@reqres.in")
                        .build());

        mockMvc.perform(post(URL_TEMPLATE_ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(order))
                .andExpect(status().isCreated());
    }

    @Test
    void createOrder_orderAlreadyExists_throwOrderAlreadyExistsException() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setBody(new Gson().toJson(TestDataGenerator.getMockResponse()))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value()));

        var order =  new Gson()
                .toJson(Order.builder()
                        .productID(34)
                        .firstName("George")
                        .lastName("Bluth")
                        .email("george.bluth@reqres.in")
                        .build());

        mockMvc.perform(post(URL_TEMPLATE_ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(order))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createOrder_emailDoesNotExist_throwEmailDoesNotExistException() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setBody(new Gson().toJson(TestDataGenerator.getMockResponse()))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value()));

        var order =  new Gson()
                .toJson(Order.builder()
                        .productID(1000)
                        .firstName("test")
                        .lastName("user")
                        .email("test.userdoesnotexist@reqres.in")
                        .build());

        mockMvc.perform(post(URL_TEMPLATE_ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(order))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createOrder_ReqresApiReturnsNull_throwReqresApiException() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setBody(new Gson().toJson(Response.builder().build()))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value()));

        var order =  new Gson()
                .toJson(Order.builder()
                        .productID(1000)
                        .firstName("test")
                        .lastName("user")
                        .email("test.user@reqres.in")
                        .build());

        mockMvc.perform(post(URL_TEMPLATE_ORDERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(order))
                .andExpect(status().isInternalServerError());
    }
}
