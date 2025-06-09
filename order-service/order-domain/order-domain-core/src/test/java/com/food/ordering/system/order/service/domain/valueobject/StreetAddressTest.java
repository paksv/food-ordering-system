package com.food.ordering.system.order.service.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StreetAddressTest {

    @Test
    void testEquality() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        StreetAddress address1 = new StreetAddress(id1, "Main Street", "12345", "New York");
        StreetAddress address2 = new StreetAddress(id2, "Main Street", "12345", "New York");
        StreetAddress address3 = new StreetAddress(UUID.randomUUID(), "Broadway", "12345", "New York");
        
        // ID is not considered in equality, only street, postalCode, and city
        assertEquals(address1, address2);
        assertNotEquals(address1, address3);
    }
    
    @Test
    void testHashCode() {
        UUID id = UUID.randomUUID();
        StreetAddress address1 = new StreetAddress(id, "Main Street", "12345", "New York");
        StreetAddress address2 = new StreetAddress(id, "Main Street", "12345", "New York");
        
        assertEquals(address1.hashCode(), address2.hashCode());
    }
    
    @Test
    void testGetters() {
        UUID id = UUID.randomUUID();
        String street = "Main Street";
        String postalCode = "12345";
        String city = "New York";
        
        StreetAddress address = new StreetAddress(id, street, postalCode, city);
        
        assertEquals(id, address.getId());
        assertEquals(street, address.getStreet());
        assertEquals(postalCode, address.getPostalCode());
        assertEquals(city, address.getCity());
    }
    
    @Test
    void testDifferentStreet() {
        UUID id = UUID.randomUUID();
        StreetAddress address1 = new StreetAddress(id, "Main Street", "12345", "New York");
        StreetAddress address2 = new StreetAddress(id, "Broadway", "12345", "New York");
        
        assertNotEquals(address1, address2);
    }
    
    @Test
    void testDifferentPostalCode() {
        UUID id = UUID.randomUUID();
        StreetAddress address1 = new StreetAddress(id, "Main Street", "12345", "New York");
        StreetAddress address2 = new StreetAddress(id, "Main Street", "54321", "New York");
        
        assertNotEquals(address1, address2);
    }
    
    @Test
    void testDifferentCity() {
        UUID id = UUID.randomUUID();
        StreetAddress address1 = new StreetAddress(id, "Main Street", "12345", "New York");
        StreetAddress address2 = new StreetAddress(id, "Main Street", "12345", "Boston");
        
        assertNotEquals(address1, address2);
    }
}