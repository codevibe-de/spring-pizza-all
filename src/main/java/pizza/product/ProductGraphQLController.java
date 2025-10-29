package pizza.product;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class ProductGraphQLController {

    private final ProductService productService;

    public ProductGraphQLController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> products() {
        Iterable<Product> iterable = productService.getAllProducts();
        return StreamSupport.stream(iterable.spliterator(), false)
                .toList();
    }

}
