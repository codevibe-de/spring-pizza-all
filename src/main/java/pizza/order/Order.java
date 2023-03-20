package pizza.order;

import pizza.customer.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    //
    // --- fields ---
    //

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    private Double totalPrice;

    private LocalDateTime estimatedTimeOfDelivery;

    //
    // --- constructors ---
    //

    public Order() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getEstimatedTimeOfDelivery() {
        return estimatedTimeOfDelivery;
    }

    public void setEstimatedTimeOfDelivery(LocalDateTime estimatedTimeOfDelivery) {
        this.estimatedTimeOfDelivery = estimatedTimeOfDelivery;
    }

    //
    // --- other methods ---
    //

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
