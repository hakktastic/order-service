package nl.hakktastic.order.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@lombok.Data
public class Data {
    Integer id;
    String email;
    String first_name;
    String last_name;
    String avatar;
}
