package pizza.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateCustomerRequest {

    private String fullName;

    private Address address;

    private String phoneNumber;

}
