package pizza.customer;

public record CreateCustomerRequest(
        String fullName,
        Address address,
        String phoneNumber
) {
}
