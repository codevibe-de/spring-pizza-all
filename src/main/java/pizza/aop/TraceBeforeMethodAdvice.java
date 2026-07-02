package pizza.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public class TraceBeforeMethodAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        var argsString = StringUtils.arrayToDelimitedString(args, ",");
        System.out.printf("About to execute %s(%s)%n",
                method.getName(),
                argsString
        );
    }

}
