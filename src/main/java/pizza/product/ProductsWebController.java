package pizza.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsWebController {

    public static final String ROOT_PATH = "/web/products";
    public static final String SINGLE_PATH = ROOT_PATH + "/{id}";

    private final ProductService productService;

    public ProductsWebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(SINGLE_PATH)
    public ModelAndView renderSingleProduct(@PathVariable String id) {
        Product product = this.productService.getProduct(id);
        return new ModelAndView("products/single-product", "product", product);
    }
}
