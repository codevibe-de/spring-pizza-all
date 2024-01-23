package demo.datajpa;

import pizza.order.Order;

import java.util.List;

public interface CustomizedOrderRepository {

    List<Order> findOverdueOrders();

}
