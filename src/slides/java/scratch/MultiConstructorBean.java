package scratch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MultiConstructorBean {

    private final StringBuilder stringBuilder;

    public MultiConstructorBean(StringBuilder sb) {
        this.stringBuilder = sb;
    }

    @Autowired
    public MultiConstructorBean(List<StringBuilder> builders, OtherBean other) {
        this(builders.iterator().next());
        // do something with OtherBean
        builders.forEach(System.out::println);
    }
}
