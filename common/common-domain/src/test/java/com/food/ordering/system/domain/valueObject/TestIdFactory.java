package com.food.ordering.system.domain.valueObject;

import java.util.UUID;

/**
 * Factory class for creating ID value objects for testing purposes.
 */
public class TestIdFactory {
    
    public static CustomerId createCustomerId(UUID value) {
        return new CustomerId(value);
    }
    
    public static RestaurantId createRestaurantId(UUID value) {
        return new RestaurantId(value);
    }
    
    public static ProductId createProductId(UUID value) {
        return new ProductId(value);
    }
    
    public static OrderId createOrderId(UUID value) {
        return new OrderId(value);
    }
}