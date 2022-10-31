package de.auinger.training.spring_boot.customer;

import org.springframework.lang.NonNull;
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

    @NonNull
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        // TODO
        throw new CustomerNotFoundException("For phoneNumber `" + phoneNumber + "`");
    }

    @NonNull
    public Iterable<Customer> getAllCustomers() {
        return Collections.unmodifiableList(this.customers);
    }

    @NonNull
    public Customer createCustomer(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(new Random().nextLong());
        }
        this.customers.add(customer);
        return customer;
    }

}
