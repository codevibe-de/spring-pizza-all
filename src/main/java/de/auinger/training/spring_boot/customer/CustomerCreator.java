package de.auinger.training.spring_boot.customer;

import java.util.Collection;

public class CustomerCreator implements Runnable {

    private CustomerService customerService;
    private Collection<Customer> customers;

    public CustomerCreator() {
        // this constructor is empty "by bad design" - so we must use setCustomerService() in CDI
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public void run() {
        customers.forEach(customerService::createCustomer);
    }
}
