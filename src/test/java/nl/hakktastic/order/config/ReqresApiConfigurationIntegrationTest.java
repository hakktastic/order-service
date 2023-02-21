package nl.hakktastic.order.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@EnableConfigurationProperties
@SpringBootTest
class ReqresApiConfigurationIntegrationTest {

    @Autowired
    ReqresApiConfiguration reqresApiConfiguration;

    @Test
    void getBaseUrl_bindingPropertiesFile_baseUrlIsSet(){

        var baseUrl = reqresApiConfiguration.getBaseUrl();
        var expectedBaseUrl = "http://localhost:9092";

        assertThat(baseUrl)
                .isNotBlank()
                .isEqualTo(expectedBaseUrl);
    }
}
