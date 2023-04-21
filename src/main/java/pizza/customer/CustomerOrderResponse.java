package pizza.customer;

import pizza.order.Order;

import java.time.LocalDateTime;

public record CustomerOrderResponse(
        long id,
        double totalPrice,
        LocalDateTime estimatedTimeOfDelivery
) {
    public static CustomerOrderResponse of(Order order) {
        return new CustomerOrderResponse(
                order.getId(),
                order.getTotalPrice(),
                order.getEstimatedTimeOfDelivery()
        );
    }
}
