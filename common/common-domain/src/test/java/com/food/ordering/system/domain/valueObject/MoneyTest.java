package com.food.ordering.system.domain.valueObject;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void testIsGreaterThanZero() {
        Money positiveAmount = new Money(new BigDecimal("10.00"));
        Money zeroAmount = new Money(new BigDecimal("0.00"));
        Money negativeAmount = new Money(new BigDecimal("-10.00"));

        assertTrue(positiveAmount.isGreaterThanZero());
        assertFalse(zeroAmount.isGreaterThanZero());
        assertFalse(negativeAmount.isGreaterThanZero());
    }

    @Test
    void testIsGreaterThan() {
        Money higherAmount = new Money(new BigDecimal("20.00"));
        Money lowerAmount = new Money(new BigDecimal("10.00"));
        Money equalAmount = new Money(new BigDecimal("10.00"));

        assertTrue(higherAmount.isGreaterThan(lowerAmount));
        assertFalse(lowerAmount.isGreaterThan(higherAmount));
        assertFalse(lowerAmount.isGreaterThan(equalAmount));
    }

    @Test
    void testAdd() {
        Money firstAmount = new Money(new BigDecimal("10.00"));
        Money secondAmount = new Money(new BigDecimal("15.50"));
        Money result = firstAmount.add(secondAmount);

        assertEquals(new BigDecimal("25.50"), result.getAmount());
    }

    @Test
    void testSubtract() {
        Money firstAmount = new Money(new BigDecimal("20.00"));
        Money secondAmount = new Money(new BigDecimal("5.50"));
        Money result = firstAmount.subtract(secondAmount);

        assertEquals(new BigDecimal("14.50"), result.getAmount());
    }

    @Test
    void testMultiply() {
        Money amount = new Money(new BigDecimal("10.00"));
        Money result = amount.multiply(3);

        assertEquals(new BigDecimal("30.00"), result.getAmount());
    }

    @Test
    void testEquals() {
        Money firstAmount = new Money(new BigDecimal("10.00"));
        Money secondAmount = new Money(new BigDecimal("10.00"));
        Money thirdAmount = new Money(new BigDecimal("20.00"));

        assertEquals(firstAmount, secondAmount);
        assertNotEquals(firstAmount, thirdAmount);
    }

    @Test
    void testHashCode() {
        Money firstAmount = new Money(new BigDecimal("10.00"));
        Money secondAmount = new Money(new BigDecimal("10.00"));
        Money thirdAmount = new Money(new BigDecimal("20.00"));

        assertEquals(firstAmount.hashCode(), secondAmount.hashCode());
        assertNotEquals(firstAmount.hashCode(), thirdAmount.hashCode());
    }

    @Test
    void testZeroConstant() {
        assertEquals(new BigDecimal("0"), Money.ZERO.getAmount());
        assertFalse(Money.ZERO.isGreaterThanZero());
    }

    @Test
    void testScaling() {
        Money amount = new Money(new BigDecimal("10.123"));
        Money result = amount.add(new Money(new BigDecimal("0.01")));
        
        assertEquals(new BigDecimal("10.13"), result.getAmount());
        assertEquals(2, result.getAmount().scale());
    }
}