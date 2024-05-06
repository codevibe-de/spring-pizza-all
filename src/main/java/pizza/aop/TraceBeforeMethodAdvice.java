package pizza.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TraceBeforeMethodAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        var argsString = Arrays.toString(args);
        System.out.printf("About to execute %s(%s)%n",
                method.getName(),
                argsString.substring(1, argsString.length() - 2)
        );
    }

}
