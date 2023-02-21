package nl.hakktastic.order.client;

import nl.hakktastic.order.config.ReqresApiConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * {@link org.springframework.web.reactive.function.client.WebClient} for Reqres API.
 *
 * @see <a href="https://reqres.in">Reqres</a>
 */
@Component
public class ReqresWebClient {

    private final WebClient webClient;

    private final ReqresApiConfiguration configuration;


    /**
     * Constructor.
     */
    public ReqresWebClient(ReqresApiConfiguration configuration){

        this.configuration = configuration;

        this.webClient = WebClient.builder()
                .baseUrl(configuration.getBaseUrl())
                .defaultHeader(
                        "Accept", MediaType.APPLICATION_JSON_VALUE,
                        "Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public WebClient getWebClient(){

        return this.webClient;
    }
}
