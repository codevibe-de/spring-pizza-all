package pizza.customer;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    //
    // --- fields ---
    //

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;

    @Embedded
    private Address address;

    private String phoneNumber;

    private Integer orderCount = 0;

    //
    // --- constructors ---
    //

    public Customer() {
    }

    public Customer(String fullName, Address address, String phoneNumber) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(Long id, String fullName, Address address, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //
    // --- get / set ---
    //

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        if (this.id != null) {
            throw new IllegalStateException("Cannot change existing id");
        }
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    //
    // --- other methods ---
    //

    public int increaseOrderCount() {
        return this.orderCount++;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderCount=" + orderCount +
                '}';
    }
}
