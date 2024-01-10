package pizza;

import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.InMemoryProductRepository;
import pizza.product.ProductService;
import summer.BeanContainer;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class PizzaApp {

    public static void main(String[] args) {
        // define beans
        BeanContainer beanContainer = new BeanContainer();
        beanContainer.defineBean("inMemPrdRepo", InMemoryProductRepository.class);
        beanContainer.defineBean("prdService", ProductService.class);
        beanContainer.defineBean("cstmService", CustomerService.class);
        beanContainer.defineBean("orderService", OrderService.class);
        beanContainer.defineBean("sample", DataLoader.Sample.class);
        beanContainer.refresh();

        // query and use beans
        beanContainer.getBean(DataLoader.class).run();
        ProductService productService = beanContainer.getBean(ProductService.class);
        CustomerService customerService = beanContainer.getBean(CustomerService.class);
        OrderService orderService = beanContainer.getBean(OrderService.class);

        // get a product
        var product = productService.getProduct("P-10");
        System.out.println(product);

        // get a customer
        var customer = customerService.getCustomerByPhoneNumber("+49 123 456789");
        System.out.println(customer);

        // place an order
        var order = orderService.placeOrder(
                "+49 123 456789",
                ofEntries(
                        entry("P-10", 3),
                        entry("S-03", 1)
                )
        );
        System.out.println(order);
    }
}
