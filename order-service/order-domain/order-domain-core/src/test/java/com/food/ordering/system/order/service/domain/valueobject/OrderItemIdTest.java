package com.food.ordering.system.order.service.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemIdTest {

    @Test
    void testEqualsAndHashCode() {
        OrderItemId id1 = new OrderItemId(1L);
        OrderItemId id2 = new OrderItemId(1L);
        OrderItemId id3 = new OrderItemId(2L);
        
        // Test equals
        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        
        // Test hashCode
        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
    }
    
    @Test
    void testGetValue() {
        Long value = 1L;
        OrderItemId id = new OrderItemId(value);
        
        assertEquals(value, id.getValue());
    }
}