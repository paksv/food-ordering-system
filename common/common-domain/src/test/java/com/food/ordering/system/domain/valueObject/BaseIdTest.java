package com.food.ordering.system.domain.valueObject;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BaseIdTest {

    // Concrete implementation of BaseId for testing
    static class TestId extends BaseId<UUID> {
        public TestId(UUID value) {
            super(value);
        }
    }

    @Test
    void testGetValue() {
        UUID id = UUID.randomUUID();
        TestId testId = new TestId(id);
        
        assertEquals(id, testId.getValue());
    }
    
    @Test
    void testEquals() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        
        TestId testId1 = new TestId(id1);
        TestId testId2 = new TestId(id1); // Same UUID
        TestId testId3 = new TestId(id2); // Different UUID
        
        // Test reflexivity
        assertEquals(testId1, testId1);
        
        // Test symmetry
        assertEquals(testId1, testId2);
        assertEquals(testId2, testId1);
        
        // Test with different values
        assertNotEquals(testId1, testId3);
        
        // Test with null and different type
        assertNotEquals(testId1, null);
        assertNotEquals(testId1, "not an id");
    }
    
    @Test
    void testHashCode() {
        UUID id = UUID.randomUUID();
        TestId testId1 = new TestId(id);
        TestId testId2 = new TestId(id);
        
        // Same value should produce same hash code
        assertEquals(testId1.hashCode(), testId2.hashCode());
    }
}