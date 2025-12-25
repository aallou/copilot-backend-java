package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloDtoTest {

    @Test
    void noArgConstructorAndSettersWork() {
        // Given
        HelloDto dto = new HelloDto();

        // When
        dto.setMessage("hi");

        // Then
        assertThat(dto.getMessage()).isEqualTo("hi");
    }

    @Test
    void allArgsConstructorWorks() {
        // Given / When
        HelloDto dto = new HelloDto("Hello!");

        // Then
        assertThat(dto.getMessage()).isEqualTo("Hello!");
    }
}
