package pizza.customer;

import org.springframework.http.HttpStatus;
import pizza.error.ManagedException;

public class CustomerNotFoundException extends ManagedException {

    public CustomerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
