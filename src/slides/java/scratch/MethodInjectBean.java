package scratch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MethodInjectBean {

    @Autowired(required = false)
    public void receiveBeans(StringBuilder stringBuilder, OtherBean otherBean) {
        // ...
    }

}
