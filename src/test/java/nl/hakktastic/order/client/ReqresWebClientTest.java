package nl.hakktastic.order.client;

import nl.hakktastic.order.config.ReqresApiConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ReqresWebClientTest {

    @InjectMocks
    ReqresWebClient reqresWebClient;

    @Mock
    ReqresApiConfiguration reqresApiConfiguration;

    @Test
    void getWebClient_returnClient(){

        var result = reqresWebClient.getWebClient();

        assertThat(result).isNotNull();
    }
}
