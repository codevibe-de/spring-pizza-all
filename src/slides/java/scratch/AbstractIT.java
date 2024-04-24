package scratch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractIT {

    @Autowired(required = false)
    protected ObjectMapper objectMapper;

}
