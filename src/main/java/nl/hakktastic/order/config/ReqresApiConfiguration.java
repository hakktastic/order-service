package nl.hakktastic.order.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;

@Slf4j
@Data
@Validated
@Configuration
@ConfigurationProperties("nl.hakktastic.reqres-api")
public class ReqresApiConfiguration {

    @NotBlank
    private String baseUrl;

    /**
     * Log the configuration at startup.
     */
    @PostConstruct
    public void logConfiguration() {
        log.info("{}", this);
    }
}