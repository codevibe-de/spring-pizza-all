package pizza.customer;

import java.util.Collection;

public class CustomerCreator implements Runnable {

    private final CustomerService customerService;
    private Collection<Customer> customers;

    public CustomerCreator(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public void run() {
        customers.forEach(customerService::createCustomer);
    }
}
