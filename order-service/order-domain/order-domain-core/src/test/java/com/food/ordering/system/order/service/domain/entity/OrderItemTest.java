package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.valueObject.Money;
import com.food.ordering.system.domain.valueObject.OrderId;
import com.food.ordering.system.order.service.domain.TestProductId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    private OrderItem orderItem;
    private final UUID PRODUCT_ID = UUID.randomUUID();
    private final UUID ORDER_ID = UUID.randomUUID();
    private final long ITEM_ID = 1L;

    @BeforeEach
    void setUp() {
        Product product = new Product(new TestProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00")));
        
        orderItem = OrderItem.Builder.builder()
                .product(product)
                .price(new Money(new BigDecimal("50.00")))
                .quantity(2)
                .subTotal(new Money(new BigDecimal("100.00")))
                .build();
    }

    @Test
    void testInitializeOrderItem() {
        OrderId orderId = new OrderId(ORDER_ID);
        OrderItemId orderItemId = new OrderItemId(ITEM_ID);
        
        orderItem.initializedOrderItem(orderId, orderItemId);
        
        assertEquals(orderId, orderItem.getOrderId());
        assertEquals(orderItemId, orderItem.getId());
    }

    @Test
    void testIsPriceValid_WhenValid() {
        assertTrue(orderItem.isPriceValid());
    }

    @Test
    void testIsPriceValid_WhenPriceDifferentFromProductPrice() {
        Product product = new Product(new TestProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("60.00")));
        
        OrderItem invalidOrderItem = OrderItem.Builder.builder()
                .product(product)
                .price(new Money(new BigDecimal("50.00"))) // Different from product price
                .quantity(2)
                .subTotal(new Money(new BigDecimal("100.00")))
                .build();
        
        assertFalse(invalidOrderItem.isPriceValid());
    }

    @Test
    void testIsPriceValid_WhenSubtotalIncorrect() {
        OrderItem invalidOrderItem = OrderItem.Builder.builder()
                .product(new Product(new TestProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))))
                .price(new Money(new BigDecimal("50.00")))
                .quantity(2)
                .subTotal(new Money(new BigDecimal("120.00"))) // Should be 100.00
                .build();
        
        assertFalse(invalidOrderItem.isPriceValid());
    }

    @Test
    void testIsPriceValid_WhenPriceIsZero() {
        OrderItem invalidOrderItem = OrderItem.Builder.builder()
                .product(new Product(new TestProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("0.00"))))
                .price(new Money(new BigDecimal("0.00")))
                .quantity(2)
                .subTotal(new Money(new BigDecimal("0.00")))
                .build();
        
        assertFalse(invalidOrderItem.isPriceValid());
    }
}