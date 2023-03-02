package pizza.product;

// TODO this class could also use Lombok annotations (same as Product or Order)
public class Product {

    //
    // --- fields ---
    //

    private String productId;

    private String name;

    private Double price;

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
