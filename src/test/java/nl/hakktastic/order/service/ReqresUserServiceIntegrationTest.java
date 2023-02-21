package nl.hakktastic.order.service;

import com.google.gson.Gson;
import nl.hakktastic.order.exception.ReqresApiException;
import nl.hakktastic.order.testdata.TestDataGenerator;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ReqresUserServiceIntegrationTest {

    @Autowired
    private ReqresUserService service;

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
    void emailExists_existingEmailProvided_returnTrue() throws InterruptedException, ReqresApiException {

        mockWebServer.enqueue(new MockResponse()
                .setBody(new Gson().toJson(TestDataGenerator.getMockResponse()))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value()));

        var actualResult = service.emailExists("test.user@reqres.in");

        assertThat(actualResult).isTrue();
    }

    @Test
    void emailExists_nonExistingEmailProvided_returnFalse() throws ReqresApiException {

        mockWebServer.enqueue(new MockResponse()
                .setBody(new Gson().toJson(TestDataGenerator.getMockResponse()))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value()));

        var actualResult = service.emailExists("user.doesnotexist@reqres.in");

        assertThat(actualResult).isFalse();
    }
}
