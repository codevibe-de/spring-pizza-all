package scratch;

import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Reflection {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        // statischer Zugriff
        Class<?> repoClass = CustomerService.class;

        // get-Methode auf Instanz
        Class<?> myClass = new CustomerService().getClass();

        //
        for (Constructor<?> c : OrderService.class.getConstructors()) {
            Class<?>[] parameterTypes = c.getParameterTypes();
            System.out.println("Constructor with " + parameterTypes.length + " parameters:");
            for (Class<?> pt : parameterTypes) {
                System.out.println("  - " + pt.getSimpleName());
            }
        }

        //
        var constructorArgs = new Object[]{new HashMapProductRepository()};
        Object untypedInstance = ProductService.class
                .getConstructors()[0]
                .newInstance(constructorArgs);
        ProductService productService = (ProductService) untypedInstance;
    }
}
