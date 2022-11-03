package pizza.order;

import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(value = "/place-order")
public class PlaceOrderServlet extends HttpServlet {

    @Setter
    private OrderService orderService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/plain");

            String phoneNumber = request.getParameter("ph");
            String[] productIdsArray = request.getParameterValues("pid");
            if (ObjectUtils.isEmpty(phoneNumber) || ObjectUtils.isEmpty(productIdsArray)) {
                response.getWriter().println("Aborting order -- not enough input data");
                return;
            }

            var productIds = Arrays.asList(productIdsArray);
            var quantitiesByProductId = productIds.stream()
                    .collect(Collectors.groupingBy(
                            pid -> pid,
                            Collectors.summingInt(pid -> 1))
                    );

            var order = this.orderService.placeOrder(phoneNumber, quantitiesByProductId);
            response.getWriter().println("Order placed (#" + order.getId() + ")");
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
