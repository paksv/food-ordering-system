package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.valueObject.CustomerId;
import java.util.UUID;

public class TestCustomerId extends CustomerId {
    public TestCustomerId(UUID value) {
        super(value);
    }
}