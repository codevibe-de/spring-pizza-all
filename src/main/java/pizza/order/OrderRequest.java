package pizza.order;

import java.util.Map;

public class OrderRequest {

    public Map<String, Integer> itemQuantities;

    @Override
    public String toString() {
        return "IncomingOrderDto{" +
                "itemQuantities=" + itemQuantities +
                '}';
    }
}
