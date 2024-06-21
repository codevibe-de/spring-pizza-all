package scratch;

import org.springframework.beans.factory.annotation.Autowired;
import pizza.product.ProductRepository;

public class AbstractProductIT extends AbstractIT {

    @Autowired
    protected ProductRepository productRepository;

}
