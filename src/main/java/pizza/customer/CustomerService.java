package pizza.customer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    //
    // constructors and setup
    //

    public CustomerService() {
    }

    //
    // business logic
    //

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return this.customers.stream()
                .filter(c -> phoneNumber.equals(c.getPhoneNumber()))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("For phoneNumber `" + phoneNumber + "`"));
    }

    public Iterable<Customer> getAllCustomers() {
        return Collections.unmodifiableList(this.customers);
    }

    public Customer createCustomer(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(Math.abs(new Random().nextLong()));
        }
        this.customers.add(customer);
        return customer;
    }

}
