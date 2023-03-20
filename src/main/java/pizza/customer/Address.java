package pizza.customer;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    //
    // --- fields ---
    //

    private String street;

    private String postalCode;

    private String city;

    //
    // --- constructors ---
    //

    public Address() {
    }

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

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
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
