package pizza.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pizza.customer.CustomerNotFoundException;
import pizza.product.ProductNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({CustomerNotFoundException.class, ProductNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundExceptions(ManagedException ex) {
        return ExceptionDto.of(ex);
    }

    @ResponseBody
    @ExceptionHandler(ManagedException.class)
    public ResponseEntity<ExceptionDto> handleManagedException(ManagedException ex) {
        return new ResponseEntity<>(
                ExceptionDto.of(ex),
                ex.getStatus());
    }
}
