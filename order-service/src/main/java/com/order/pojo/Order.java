package com.order.pojo;

public class Order {
    private String orderId;
    private String orderTime;
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
