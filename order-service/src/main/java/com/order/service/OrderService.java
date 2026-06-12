package com.order.service;

import com.order.pojo.Order;
import java.util.List;

/**
 * 订单服务接口（模拟数据版）
 */
public interface OrderService {
    /**
     * 获取我的订单列表
     *
     * @return 订单列表
     */
    List<Order> getMyOrders();
}
