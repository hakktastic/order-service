package nl.hakktastic.order.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * JPA Order Entity.
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "product_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @Positive(message = "Product ID should be positive")
    private int productID;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @NotBlank(message = "Last name should be provided")
    @Size(max = 256, message = "First name should be max. 256 characters")
    private String firstName;

    @NotBlank(message = "Last name should be provided")
    @Size(max = 256, message = "Last name should be max. 256 characters")
    private String lastName;
}
