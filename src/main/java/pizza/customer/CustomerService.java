package pizza.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    public Customer getCustomer(long customerId) {
        return customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("For id `" + customerId + "`"));
    }

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increaseOrderCount(long customerId) {
        // obtain entity of customer valid for the new transaction that was started for this method
        Optional<Customer> customerFromThisTrx = this.customerRepository.findById(customerId);

        // customer might not yet be visible to this transaction in case it just has been created
        customerFromThisTrx.ifPresent(Customer::increaseOrderCount);
    }
}
