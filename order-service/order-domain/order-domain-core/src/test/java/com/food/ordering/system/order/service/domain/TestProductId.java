package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.valueObject.ProductId;
import java.util.UUID;

public class TestProductId extends ProductId {
    public TestProductId(UUID value) {
        super(value);
    }
}