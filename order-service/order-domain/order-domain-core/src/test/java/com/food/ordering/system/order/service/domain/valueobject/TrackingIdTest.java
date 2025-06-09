package com.food.ordering.system.order.service.domain.valueobject;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrackingIdTest {

    @Test
    void testEqualsAndHashCode() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        
        TrackingId id1 = new TrackingId(uuid1);
        TrackingId id2 = new TrackingId(uuid1);
        TrackingId id3 = new TrackingId(uuid2);
        
        // Test equals
        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        
        // Test hashCode
        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
    }
    
    @Test
    void testGetValue() {
        UUID uuid = UUID.randomUUID();
        TrackingId id = new TrackingId(uuid);
        
        assertEquals(uuid, id.getValue());
    }
}