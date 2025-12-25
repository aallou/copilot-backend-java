package com.example.demo.service;

import com.example.demo.dto.HelloDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    private final HelloService svc = new HelloService();

    @Test
    @DisplayName("getHello returns greeting with provided name (happy path)")
    void getHello_happyPath() {
        // Given
        String name = "Alice";

        // When
        HelloDto dto = svc.getHello(name);

        // Then
        assertThat(dto).isNotNull();
        assertThat(dto.getMessage()).isEqualTo("Hello, Alice!");
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test
        @DisplayName("getHello handles null name by returning 'Hello, null!'")
        void getHello_nullName() {
            // Given
            String name = null;

            // When
            HelloDto dto = svc.getHello(name);

            // Then
            assertThat(dto).isNotNull();
            assertThat(dto.getMessage()).isEqualTo("Hello, null!");
        }

        @Test
        @DisplayName("getHello handles empty name")
        void getHello_emptyName() {
            // Given
            String name = "";

            // When
            HelloDto dto = svc.getHello(name);

            // Then
            assertThat(dto).isNotNull();
            assertThat(dto.getMessage()).isEqualTo("Hello, !");
        }
    }
}
