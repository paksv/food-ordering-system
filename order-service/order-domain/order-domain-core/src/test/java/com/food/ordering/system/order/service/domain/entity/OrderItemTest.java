package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.valueObject.Money;
import com.food.ordering.system.domain.valueObject.OrderId;
import com.food.ordering.system.domain.valueObject.ProductId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void testInitializeOrderItem() {
        // Create test objects
        OrderItem orderItem = createOrderItem();
        OrderId orderId = createOrderId();
        OrderItemId orderItemId = new OrderItemId(1L);
        
        // Initialize order item
        orderItem.initializedOrderItem(orderId, orderItemId);
        
        // Verify order item is initialized correctly
        assertEquals(orderId, orderItem.getOrderId());
        assertEquals(orderItemId, orderItem.getId());
    }
    
    @Test
    void testIsPriceValidWithValidPrice() {
        // Create product with price 10.00
        Money productPrice = new Money(new BigDecimal("10.00"));
        Product product = createProduct(productPrice);
        
        // Create order item with matching price and correct subtotal
        OrderItem orderItem = OrderItem.Builder.builder()
                .product(product)
                .price(productPrice)
                .quantity(2)
                .subTotal(new Money(new BigDecimal("20.00"))) // 10.00 * 2
                .build();
        
        // Price should be valid
        assertTrue(orderItem.isPriceValid());
    }
    
    @Test
    void testIsPriceValidWithInvalidProductPrice() {
        // Create product with price 10.00
        Money productPrice = new Money(new BigDecimal("10.00"));
        Product product = createProduct(productPrice);
        
        // Create order item with different price
        OrderItem orderItem = OrderItem.Builder.builder()
                .product(product)
                .price(new Money(new BigDecimal("12.00"))) // Different from product price
                .quantity(2)
                .subTotal(new Money(new BigDecimal("24.00")))
                .build();
        
        // Price should be invalid
        assertFalse(orderItem.isPriceValid());
    }
    
    @Test
    void testIsPriceValidWithInvalidSubtotal() {
        // Create product with price 10.00
        Money productPrice = new Money(new BigDecimal("10.00"));
        Product product = createProduct(productPrice);
        
        // Create order item with correct price but wrong subtotal
        OrderItem orderItem = OrderItem.Builder.builder()
                .product(product)
                .price(productPrice)
                .quantity(2)
                .subTotal(new Money(new BigDecimal("25.00"))) // Should be 20.00
                .build();
        
        // Price should be invalid
        assertFalse(orderItem.isPriceValid());
    }
    
    @Test
    void testIsPriceValidWithZeroPrice() {
        // Create product with price 0.00
        Money productPrice = new Money(BigDecimal.ZERO);
        Product product = createProduct(productPrice);
        
        // Create order item with zero price
        OrderItem orderItem = OrderItem.Builder.builder()
                .product(product)
                .price(productPrice)
                .quantity(2)
                .subTotal(new Money(BigDecimal.ZERO))
                .build();
        
        // Price should be invalid because it's not greater than zero
        assertFalse(orderItem.isPriceValid());
    }
    
    /**
     * Helper method to create a product for testing
     */
    private Product createProduct(Money price) {
        ProductId productId = Mockito.mock(ProductId.class);
        Mockito.when(productId.getValue()).thenReturn(UUID.randomUUID());
        return new Product(productId, "Test Product", price);
    }
    
    /**
     * Helper method to create an order item for testing
     */
    private OrderItem createOrderItem() {
        Money price = new Money(new BigDecimal("10.00"));
        return OrderItem.Builder.builder()
                .product(createProduct(price))
                .price(price)
                .quantity(1)
                .subTotal(price)
                .build();
    }
    
    /**
     * Helper method to create an order ID for testing
     */
    private OrderId createOrderId() {
        OrderId orderId = Mockito.mock(OrderId.class);
        Mockito.when(orderId.getValue()).thenReturn(UUID.randomUUID());
        return orderId;
    }
}