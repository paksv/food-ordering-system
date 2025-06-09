package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.valueObject.RestaurantId;
import java.util.UUID;

public class TestRestaurantId extends RestaurantId {
    public TestRestaurantId(UUID value) {
        super(value);
    }
}