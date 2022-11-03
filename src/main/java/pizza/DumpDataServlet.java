package pizza;

import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.order.Order;
import pizza.order.OrderService;
import pizza.product.Product;
import pizza.product.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/dump")
public class DumpDataServlet extends HttpServlet {

    private final CustomerService customerService;

    private final ProductService productService;

    private final OrderService orderService;

    public DumpDataServlet(CustomerService customerService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/plain");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            response.getWriter().println("PRODUCTS:");
            for (Product p : productService.getAllProducts()) {
                var repr = String.format("  #%s: %s (%.2f€)",
                        p.getProductId(), p.getName(), p.getPrice());
                response.getWriter().println(repr);
            }

            response.getWriter().println("\nCUSTOMERS:");
            for (Customer customer : customerService.getAllCustomers()) {
                var repr = String.format("  #%d: %s, %s (ph: %s)",
                        customer.getId(), customer.getFullName(), customer.getAddress().getCity(), customer.getPhoneNumber());
                response.getWriter().println(repr);
            }

            response.getWriter().println("\nORDERS:");
            for (Order o : orderService.getOrders()) {
                var repr = String.format("  #%d: %s, %s (%.2f€) %s",
                        o.getId(), o.getCustomer().getFullName(), o.getCustomer().getAddress().getCity(),
                        o.getTotalPrice(), o.getEstimatedTimeOfDelivery().toString());
                response.getWriter().println(repr);
            }

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
