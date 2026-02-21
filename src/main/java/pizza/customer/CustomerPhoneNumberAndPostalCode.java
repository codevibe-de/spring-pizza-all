package pizza.customer;

public interface CustomerPhoneNumberAndPostalCode {
    String getPhoneNumber();

    AddressPostalCode getAddress();

    interface AddressPostalCode {
        String getPostalCode();
    }
}
