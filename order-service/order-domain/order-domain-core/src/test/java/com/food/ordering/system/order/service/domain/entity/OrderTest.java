package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.valueObject.*;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testInitializeOrder() {
        // Create test objects using mocks for IDs
        Product product = createProduct();
        OrderItem orderItem = createOrderItem(product);
        Order order = createOrder(List.of(orderItem));
        
        // Initialize order
        order.initializeOrder();
        
        // Verify order is initialized correctly
        assertNotNull(order.getId());
        assertNotNull(order.getTrackingId());
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertNotNull(order.getItems().get(0).getId());
        assertEquals(order.getId(), order.getItems().get(0).getOrderId());
    }
    
    @Test
    void testValidateOrder() {
        // Create test objects using mocks for IDs
        Product product = createProduct();
        OrderItem orderItem = createOrderItem(product);
        Order order = createOrder(List.of(orderItem));
        
        // Validate order - should not throw exception
        assertDoesNotThrow(() -> order.validateOrder());
        
        // Initialize order after validation
        order.initializeOrder();
    }
    
    /**
     * Helper method to create a product for testing
     */
    private Product createProduct() {
        ProductId productId = Mockito.mock(ProductId.class);
        Mockito.when(productId.getValue()).thenReturn(UUID.randomUUID());
        return new Product(productId, "Test Product", new Money(new BigDecimal("10.00")));
    }
    
    /**
     * Helper method to create an order item for testing
     */
    private OrderItem createOrderItem(Product product) {
        return OrderItem.Builder.builder()
                .product(product)
                .price(product.getPrice())
                .quantity(1)
                .subTotal(product.getPrice())
                .build();
    }
    
    /**
     * Helper method to create an order for testing
     */
    private Order createOrder(List<OrderItem> orderItems) {
        CustomerId customerId = Mockito.mock(CustomerId.class);
        Mockito.when(customerId.getValue()).thenReturn(UUID.randomUUID());
        
        RestaurantId restaurantId = Mockito.mock(RestaurantId.class);
        Mockito.when(restaurantId.getValue()).thenReturn(UUID.randomUUID());
        
        return Order.Builder.builder()
                .customerId(customerId)
                .restaurantId(restaurantId)
                .deliveryAddress(new StreetAddress(UUID.randomUUID(), "123 Street", "12345", "City"))
                .price(new Money(new BigDecimal("10.00")))
                .items(orderItems)
                .build();
    }
}