package pizza.customer;

public class Customer {

    //
    // --- fields ---
    //

    private Long id;

    private final String fullName;

    private final Address address;

    private final String phoneNumber;

    //
    // --- constructors ---
    //

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    //
    // --- other methods ---
    //

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
