package pizza.order;

import pizza.customer.Customer;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class Order {

    //
    // --- fields ---
    //

    Long id;

    private final Customer customer;

    private final Double totalPrice;

    private final LocalDateTime estimatedTimeOfDelivery;

    //
    // --- constructors ---
    //

    public Order(Customer customer, Double totalPrice, LocalDateTime estimatedTimeOfDelivery) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.estimatedTimeOfDelivery = estimatedTimeOfDelivery;
    }

    public Order(Long id, Customer customer, Double totalPrice, LocalDateTime estimatedTimeOfDelivery) {
        this.id = id;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.estimatedTimeOfDelivery = estimatedTimeOfDelivery;
    }

    //
    // --- get / set ---
    //

    public void setId(long id) {
        if (this.id != null) {
            throw new IllegalStateException("Cannot change existing id");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getEstimatedTimeOfDelivery() {
        return estimatedTimeOfDelivery;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", totalPrice=" + totalPrice +
                ", estimatedTimeOfDelivery=" + estimatedTimeOfDelivery +
                '}';
    }
}
