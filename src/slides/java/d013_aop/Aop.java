package d013_aop;

import pizza.product.HashMapProductRepository;
import pizza.product.Product;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

import java.lang.reflect.Proxy;

public class Aop {

    public static void main(String[] args) {
        // Nutzung via Interface
        ProductRepository repo = new HashMapProductRepository();

        // Nutzung der Klasse selbst
        ProductService service = new ProductService(repo);
    }

    void dynamicProxyDemo() {
        ProductRepository productRepositoryProxy = (ProductRepository) Proxy.newProxyInstance(
                ProductRepository.class.getClassLoader(),
                new Class[]{ProductRepository.class},
                new AspectInvocationHandler(new HashMapProductRepository())
        );
    }
}

class AspectInvocationHandler implements java.lang.reflect.InvocationHandler {
    private Object obj;

    public AspectInvocationHandler(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking the method");
        Object result = method.invoke(obj, args);
        System.out.println("After invoking the method");
        return result;
    }
}


class ProductServiceProxy extends ProductService {

    public ProductServiceProxy(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    public Product getProduct(String productId) {
        System.out.println("Before invoking the method");
        Product result = super.getProduct(productId);
        System.out.println("After invoking the method");
        return result;
    }
}