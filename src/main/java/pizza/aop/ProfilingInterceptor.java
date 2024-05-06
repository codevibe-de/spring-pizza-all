package pizza.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ProfilingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startMillis = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endMillis = System.currentTimeMillis();
        System.out.printf("Execution of %s() took %d ms\n",
                invocation.getMethod().getName(),
                endMillis - startMillis);
        return result;
    }

}
