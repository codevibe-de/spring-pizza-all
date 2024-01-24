package pizza.product;

import org.springframework.http.HttpStatus;
import pizza.error.ManagedException;

public class ProductNotFoundException extends ManagedException {

    public ProductNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
