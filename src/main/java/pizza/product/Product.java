package pizza.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    //
    // --- fields ---
    //

    @Id
    @Column(name = "pk")
    String productId;

    String name;

    Double price;

    //
    // --- constructors ---
    //

    public Product() {
    }

    public Product(String productId, String name, Double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    //
    // --- get / set ---
    //

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    //
    // --- misc ---
    //

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
