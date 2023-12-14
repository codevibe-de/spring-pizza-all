package pizza.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pizza.customer.Customer;
import pizza.customer.CustomerRepository;
import pizza.customer.CustomerService;
import pizza.product.Product;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRestController.class)
@Import({OrderService.class, CustomerService.class, ProductService.class})
@AutoConfigureDataJpa
@TestPropertySource(properties = {
        "app.order.discount-days=MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY",
        "app.order.discount-rate=10.0"
})
class OrderRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setupTestData() {
        productRepository.deleteAll();
        productRepository.save(new Product("p1", "Product One", 1.00));
        customerRepository.deleteAll();
        customerRepository.save(new Customer("Toni Test", null, "040-112233"));
    }

    @Test
    void sayHello() throws Exception {
        // when
        var resultActions = this.mockMvc
                .perform(MockMvcRequestBuilders.get(OrderRestController.GREETING_ENDPOINT));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.emptyString())));
    }

    @Test
    void placeOrder() throws Exception {
        // given
        OrderRequest orderRequestData = new OrderRequest();
        orderRequestData.phoneNumber = "040-112233";
        orderRequestData.itemQuantities = Collections.singletonMap("p1", 2);

        // when
        var resultActions = this.mockMvc.perform(post(OrderRestController.PLACE_ORDER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(orderRequestData))
        );

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalPrice", Matchers.is(1.8)))
                .andExpect(jsonPath("$.customer.fullName", Matchers.is("Toni Test")));
    }

    private String toJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

}