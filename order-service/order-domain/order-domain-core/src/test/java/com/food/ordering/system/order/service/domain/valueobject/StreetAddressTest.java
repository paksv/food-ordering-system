package com.food.ordering.system.order.service.domain.valueobject;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StreetAddressTest {

    @Test
    void testEqualsAndHashCode() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        
        StreetAddress address1 = new StreetAddress(id1, "123 Main St", "12345", "New York");
        StreetAddress address2 = new StreetAddress(id2, "123 Main St", "12345", "New York");
        StreetAddress address3 = new StreetAddress(id1, "456 Oak Ave", "67890", "Los Angeles");
        
        // Test equals - should be equal if street, postalCode, and city are the same (id is ignored)
        assertEquals(address1, address2);
        assertNotEquals(address1, address3);
        
        // Test hashCode - should be the same if street, postalCode, and city are the same
        assertEquals(address1.hashCode(), address2.hashCode());
        assertNotEquals(address1.hashCode(), address3.hashCode());
    }
    
    @Test
    void testGetters() {
        UUID id = UUID.randomUUID();
        String street = "123 Main St";
        String postalCode = "12345";
        String city = "New York";
        
        StreetAddress address = new StreetAddress(id, street, postalCode, city);
        
        assertEquals(id, address.getId());
        assertEquals(street, address.getStreet());
        assertEquals(postalCode, address.getPostalCode());
        assertEquals(city, address.getCity());
    }
    
    @Test
    void testDifferentAddressesWithSameId() {
        UUID id = UUID.randomUUID();
        
        StreetAddress address1 = new StreetAddress(id, "123 Main St", "12345", "New York");
        StreetAddress address2 = new StreetAddress(id, "456 Oak Ave", "67890", "Los Angeles");
        
        // Different addresses should not be equal even with the same id
        assertNotEquals(address1, address2);
    }
    
    @Test
    void testSameAddressWithDifferentId() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        
        StreetAddress address1 = new StreetAddress(id1, "123 Main St", "12345", "New York");
        StreetAddress address2 = new StreetAddress(id2, "123 Main St", "12345", "New York");
        
        // Same address with different ids should be equal
        assertEquals(address1, address2);
    }
}