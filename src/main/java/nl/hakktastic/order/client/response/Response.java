package nl.hakktastic.order.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@lombok.Data
public class Response {
    Integer page;
    Integer per_page;
    Integer total;
    Integer total_pages;
    List<Data> data;
}
