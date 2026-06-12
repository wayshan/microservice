package com.order.controller;

import com.order.pojo.Order;
import com.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 订单控制器（模拟数据版）
 * <p>提供订单查询接口，当前使用模拟数据，后续可替换为数据库访问。</p>
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /** 通过构造器注入 OrderService */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 获取我的订单列表
     *
     * @return 订单列表
     */
    @GetMapping("/myOrder")
    public List<Order> getMyOrders() {
        return orderService.getMyOrders();
    }
}
