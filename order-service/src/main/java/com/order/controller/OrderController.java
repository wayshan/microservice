package com.order.controller;

import com.common.result.ResponseResult;
import com.order.pojo.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 订单控制器（模拟数据版）
 * <p>提供订单查询接口，当前使用模拟数据，后续可替换为数据库访问。</p>
 */
@RefreshScope  // 配置变更时自动重建 Bean
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Value("${dy.test:}")
    private String stest ; // Consul 中改了，这里自动更新

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
    public ResponseResult getMyOrders() {
        List<Order> list = orderService.getMyOrders();
        return ResponseResult.ok("list", list);
    }


    @GetMapping("/stest")
    public ResponseResult getTest(){
        return ResponseResult.ok("stest", stest);
    }
}
