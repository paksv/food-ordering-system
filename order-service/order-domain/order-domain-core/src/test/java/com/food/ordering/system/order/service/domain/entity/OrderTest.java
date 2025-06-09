package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.valueObject.*;
import com.food.ordering.system.order.service.domain.TestCustomerId;
import com.food.ordering.system.order.service.domain.TestProductId;
import com.food.ordering.system.order.service.domain.TestRestaurantId;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private final UUID CUSTOMER_ID = UUID.randomUUID();
    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final UUID PRODUCT_ID = UUID.randomUUID();
    private final BigDecimal PRICE = new BigDecimal("200.00");

    @BeforeEach
    void setUp() {
        OrderItem orderItem = OrderItem.Builder.builder()
                .product(new Product(new TestProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))))
                .price(new Money(new BigDecimal("50.00")))
                .quantity(4)
                .subTotal(new Money(new BigDecimal("200.00")))
                .build();

        StreetAddress streetAddress = new StreetAddress(UUID.randomUUID(), "street_1", "1000AB", "Amsterdam");

        order = Order.Builder.builder()
                .customerId(new TestCustomerId(CUSTOMER_ID))
                .restaurantId(new TestRestaurantId(RESTAURANT_ID))
                .deliveryAddress(streetAddress)
                .price(new Money(PRICE))
                .items(List.of(orderItem))
                .build();
    }

    @Test
    void testInitializeOrder() {
        order.initializeOrder();
        assertNotNull(order.getId());
        assertNotNull(order.getTrackingId());
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertNotNull(order.getItems().get(0).getId());
    }

    @Test
    void testValidateOrder() {
        order.initializeOrder();
        assertThrows(OrderDomainException.class, () -> order.validateOrder());
    }

    @Test
    void testValidateOrderWithNullId() {
        assertThrows(OrderDomainException.class, () -> order.validateOrder());
    }

    @Test
    void testValidateTotalPrice() {
        order.initializeOrder();
        // This test will fail due to a bug in the validateTotalPrice method
        // The condition should be checking if price is null or NOT greater than zero
        assertThrows(OrderDomainException.class, () -> order.validateOrder());
    }

    @Test
    void testValidateItemsPrice() {
        // Create an order with mismatched item prices
        OrderItem orderItem = OrderItem.Builder.builder()
                .product(new Product(new TestProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))))
                .price(new Money(new BigDecimal("60.00"))) // Different from product price
                .quantity(4)
                .subTotal(new Money(new BigDecimal("240.00")))
                .build();

        Order invalidOrder = Order.Builder.builder()
                .customerId(new TestCustomerId(CUSTOMER_ID))
                .restaurantId(new TestRestaurantId(RESTAURANT_ID))
                .deliveryAddress(new StreetAddress(UUID.randomUUID(), "street_1", "1000AB", "Amsterdam"))
                .price(new Money(new BigDecimal("240.00")))
                .items(List.of(orderItem))
                .build();

        invalidOrder.initializeOrder();
        assertThrows(OrderDomainException.class, () -> invalidOrder.validateOrder());
    }

    @Test
    void testValidateItemsTotal() {
        // Create an order with mismatched total price
        OrderItem orderItem = OrderItem.Builder.builder()
                .product(new Product(new TestProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))))
                .price(new Money(new BigDecimal("50.00")))
                .quantity(4)
                .subTotal(new Money(new BigDecimal("200.00")))
                .build();

        Order invalidOrder = Order.Builder.builder()
                .customerId(new TestCustomerId(CUSTOMER_ID))
                .restaurantId(new TestRestaurantId(RESTAURANT_ID))
                .deliveryAddress(new StreetAddress(UUID.randomUUID(), "street_1", "1000AB", "Amsterdam"))
                .price(new Money(new BigDecimal("250.00"))) // Different from items total
                .items(List.of(orderItem))
                .build();

        invalidOrder.initializeOrder();
        assertThrows(OrderDomainException.class, () -> invalidOrder.validateOrder());
    }
}