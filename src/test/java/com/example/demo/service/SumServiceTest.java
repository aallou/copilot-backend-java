package com.example.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SumServiceTest {

    private final SumService svc = new SumService();

    @Test
    @DisplayName("sum should return the sum of two positive integers (happy path)")
    void sum_returnsSum_forPositiveIntegers() {
        // Given
        int a = 2;
        int b = 3;

        // When
        int result = svc.sum(a, b);

        // Then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("sum should handle negative values correctly")
    void sum_handlesNegativeValues() {
        // Given
        int a = -4;
        int b = 10;

        // When
        int result = svc.sum(a, b);

        // Then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("sum should handle zero values")
    void sum_handlesZero() {
        // Given
        int a = 0;
        int b = 0;

        // When
        int result = svc.sum(a, b);

        // Then
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("sum should overflow according to Java int semantics")
    void sum_overflowBehavior() {
        // Given
        int a = Integer.MAX_VALUE;
        int b = 1;

        // When
        int result = svc.sum(a, b);

        // Then - Java int overflow wraps around to Integer.MIN_VALUE
        assertThat(result).isEqualTo(Integer.MIN_VALUE);
    }
}
