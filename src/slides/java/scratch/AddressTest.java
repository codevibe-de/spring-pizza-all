package scratch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pizza.customer.Address;

public class AddressTest {

    private final String street = "Test-Allee 1";
    private final String postalCode = "12345";
    private final String city = "Erbshausen";

    @Test
    public void instantiating() {
        Address address = new Address(street, postalCode, city);
        Assertions.assertThat(address.getStreet()).isEqualTo(street);
        Assertions.assertThat(address.getPostalCode()).isEqualTo(postalCode);
        Assertions.assertThat(address.getCity()).isEqualTo(city);
    }
}
