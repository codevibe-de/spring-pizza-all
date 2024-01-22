package scratch;

import pizza.product.HashMapProductRepository;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

public class Aop {

    public static void main(String[] args) {

        // Nutzung via Interface
        ProductRepository repo = new HashMapProductRepository();

        // Nutzung der Klasse selbst
        ProductService service = new ProductService(repo);


    }
}
