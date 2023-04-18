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
import pizza.WebSecurityConfig;
import pizza.customer.Address;
import pizza.customer.Customer;
import pizza.customer.CustomerRepository;
import pizza.customer.CustomerService;
import pizza.product.Product;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRestController.class)
@Import({OrderService.class, CustomerService.class, ProductService.class, WebSecurityConfig.class})
@AutoConfigureDataJpa
@TestPropertySource(properties = {"app.order.daily-discounts={MONDAY:'10', TUESDAY:'10', WEDNESDAY:'10', THURSDAY:'10', FRIDAY:'10', SATURDAY:'10', SUNDAY:'10'}"})
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
        customerRepository.save(
                new Customer(
                        "Toni Test",
                        new Address(null, "99988", null),
                        "040-112233"));
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
        orderRequestData.itemQuantities = Collections.singletonMap("p1", 2);

        // when
        var resultActions = this.mockMvc.perform(post(OrderRestController.PLACE_ORDER_ENDPOINT)
                .with(httpBasic("040-112233", "99988"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(orderRequestData))
        );

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalPrice", Matchers.is(1.8)))
                .andExpect(jsonPath("$.customer.fullName", Matchers.is("Toni Test")))
                .andExpect(jsonPath("$.customer.phoneNumber", Matchers.is("040-112233")));
    }

    private String toJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

}