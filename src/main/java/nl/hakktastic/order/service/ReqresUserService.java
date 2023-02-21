package nl.hakktastic.order.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.client.ReqresWebClient;
import nl.hakktastic.order.client.response.Response;
import nl.hakktastic.order.exception.ReqresApiException;
import org.springframework.stereotype.Service;

/**
 * Reqres User Service.
 *
 */
@Slf4j
@Service
public class ReqresUserService {

    private final ReqresWebClient reqresWebClient;

    /**
     * Constructor.
     *
     * @param webClient webclient for Reqres API
     */
    public ReqresUserService(ReqresWebClient webClient){

        reqresWebClient = webClient;
    }

    /**
     * Check if email exists in API.
     *
     * @param email email address to be checked
     * @return {@code TRUE} if email is found
     */
    public boolean emailExists(String email) throws ReqresApiException {

        log.debug("ReqresUserService - emailExists: check if email='{}' exists", email);

        boolean emailExists = false;

        var webClient = reqresWebClient.getWebClient();

        // TODO: update implementation to retrieve all results
        var response = webClient.get()
                .uri("/api/users")
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        if(response != null && response.getData() != null){

            log.debug("ReqresUserService - emailExists: response={}", response);

            emailExists = response
                    .getData()
                    .stream()
                    .anyMatch(user -> user.getEmail().equals(email));

        } else {

            var message = "web client returned no response";
            log.info("ReqresUserService - emailExists: {}", message);
            throw new ReqresApiException(message);
        }

        log.info("ReqresUserService - emailExists: emailExists={}", emailExists);

        return emailExists;
    }
}
