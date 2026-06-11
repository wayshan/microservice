package com.order.service;

import com.order.pojo.Order;
import java.util.List;

public interface OrderService {
    List<Order> getMyOrders();
}
