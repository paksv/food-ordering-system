package com.food.ordering.system.order.service.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StreetAddressTest {

    @Test
    void testGetters() {
        UUID id = UUID.randomUUID();
        String street = "123 Main St";
        String postalCode = "12345";
        String city = "Test City";
        
        StreetAddress address = new StreetAddress(id, street, postalCode, city);
        
        assertEquals(id, address.getId());
        assertEquals(street, address.getStreet());
        assertEquals(postalCode, address.getPostalCode());
        assertEquals(city, address.getCity());
    }
    
    @Test
    void testEqualsWithSameValues() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID(); // Different IDs
        
        StreetAddress address1 = new StreetAddress(id1, "123 Main St", "12345", "Test City");
        StreetAddress address2 = new StreetAddress(id2, "123 Main St", "12345", "Test City");
        
        // Should be equal even with different IDs because equals only compares street, postalCode, and city
        assertEquals(address1, address2);
        assertEquals(address1.hashCode(), address2.hashCode());
    }
    
    @Test
    void testEqualsWithDifferentValues() {
        UUID id = UUID.randomUUID();
        
        StreetAddress address1 = new StreetAddress(id, "123 Main St", "12345", "Test City");
        StreetAddress address2 = new StreetAddress(id, "456 Other St", "12345", "Test City");
        StreetAddress address3 = new StreetAddress(id, "123 Main St", "67890", "Test City");
        StreetAddress address4 = new StreetAddress(id, "123 Main St", "12345", "Other City");
        
        assertNotEquals(address1, address2); // Different street
        assertNotEquals(address1, address3); // Different postal code
        assertNotEquals(address1, address4); // Different city
    }
    
    @Test
    void testEqualsWithNull() {
        UUID id = UUID.randomUUID();
        StreetAddress address = new StreetAddress(id, "123 Main St", "12345", "Test City");
        
        assertNotEquals(null, address);
    }
    
    @Test
    void testEqualsWithDifferentClass() {
        UUID id = UUID.randomUUID();
        StreetAddress address = new StreetAddress(id, "123 Main St", "12345", "Test City");
        
        assertNotEquals("Not an address", address);
    }
}