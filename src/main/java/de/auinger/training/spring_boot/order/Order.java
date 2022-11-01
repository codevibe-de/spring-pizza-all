package de.auinger.training.spring_boot.order;

import de.auinger.training.spring_boot.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
public class Order {

    Long id;

    private final Customer customer;

    private final Double totalPrice;

    private final LocalDateTime estimatedTimeOfDelivery;


    public void setId(long id) {
        if (this.id != null) {
            throw new IllegalStateException("Cannot change existing id");
        }
        this.id = id;
    }

}
