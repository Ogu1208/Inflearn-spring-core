package com.example.springcore.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
