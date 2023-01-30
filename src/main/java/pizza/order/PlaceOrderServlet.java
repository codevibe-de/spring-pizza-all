package pizza.order;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(value = "/place-order")
public class PlaceOrderServlet extends HttpServlet {

    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

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

    @Override
    public void init() throws ServletException {
        super.init();

        // since this servlet has been created by Tomcat, not by Spring it hasn't been subject
        // to any CDI processing -- which is what we are doing now by hand
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ctx.getAutowireCapableBeanFactory().autowireBeanProperties(
                this,
                AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE,
                true);
    }
}
