package com.order.service.impl;

import com.order.pojo.Order;
import com.order.service.OrderService;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

/**
 * 订单服务实现（模拟数据版）
 * <p>返回硬编码的模拟订单数据，后续可替换为数据库查询。</p>
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> getMyOrders() {
        // 模拟数据 - 后续可替换为真实数据访问
        return Arrays.asList(
            new Order("ORD-001", "2023-01-15", 99.99),
            new Order("ORD-002", "2023-01-16", 149.99)
        );
    }
}
