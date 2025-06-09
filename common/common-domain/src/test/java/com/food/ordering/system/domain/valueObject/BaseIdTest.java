package com.food.ordering.system.domain.valueObject;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BaseIdTest {

    @Test
    void testEqualsAndHashCode() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        
        OrderId orderId1 = new OrderId(id1);
        OrderId orderId2 = new OrderId(id1); // Same UUID
        OrderId orderId3 = new OrderId(id2); // Different UUID
        
        // Test equals
        assertEquals(orderId1, orderId2);
        assertNotEquals(orderId1, orderId3);
        
        // Test hashCode
        assertEquals(orderId1.hashCode(), orderId2.hashCode());
        assertNotEquals(orderId1.hashCode(), orderId3.hashCode());
    }
    
    @Test
    void testGetValue() {
        UUID id = UUID.randomUUID();
        OrderId orderId = new OrderId(id);
        
        assertEquals(id, orderId.getValue());
    }
    
    @Test
    void testDifferentIdTypesWithSameValue() {
        UUID id = UUID.randomUUID();
        OrderId orderId = new OrderId(id);
        CustomerId customerId = new CustomerId(id);
        
        // In the current implementation, different ID types with the same value are considered equal
        // This is because BaseId.equals only compares the value, not the class type
        assertEquals(orderId, customerId);
        assertEquals(orderId.hashCode(), customerId.hashCode());
    }
}