package pizza.customer;

public class Address {

    //
    // --- fields ---
    //

    private final String street;

    private final String postalCode;

    private final String city;

    //
    // --- constructors ---
    //

    public Address(String street, String postalCode, String city) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    //
    // --- get / set ---
    //

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    //
    // --- other methods ---
    //

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
