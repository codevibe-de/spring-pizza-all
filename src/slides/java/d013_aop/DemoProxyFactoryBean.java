package d013_aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import pizza.customer.CustomerService;

import java.lang.reflect.Method;

public class DemoProxyFactoryBean {

    public static void main(String[] args) {
        var factory = new ProxyFactoryBean();
        factory.addAdvice(new TracingBeforeAdvice());
        //factory.setProxyInterfaces();
        factory.setTarget(new CustomerService());
        CustomerService proxy = (CustomerService) factory.getObject();
        proxy.getAllCustomers();
    }
}

class TracingBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("About to call " + method.getName() + "()");
    }
}