package com.order.pojo;

/**
 * 订单 POJO（模拟数据版）
 * <p>用于 OrderService 返回模拟订单数据，后续可替换为数据库实体。</p>
 */
public class Order {
    /** 订单编号 */
    private String orderId;
    /** 订单时间 */
    private String orderTime;
    /** 订单金额 */
    private double amount;

    public Order(String orderId, String orderTime, double amount) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.amount = amount;
    }

    public String getOrderId() { return orderId; }
    public String getOrderTime() { return orderTime; }
    public double getAmount() { return amount; }
}
