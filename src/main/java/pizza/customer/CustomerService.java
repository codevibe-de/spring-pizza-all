package pizza.customer;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    //
    // constructors and setup
    //

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //
    // business logic
    //

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException("For phoneNumber `" + phoneNumber + "`"));
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void increaseOrderCount(long customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        customer.ifPresent(Customer::increaseOrderCount);
    }
}
