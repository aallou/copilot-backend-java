package com.example.demo.service;

import com.example.demo.dto.HelloDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceExtraTest {

    private final HelloService svc = new HelloService();

    @Test
    @DisplayName("getHello trims whitespace in name")
    void getHello_trimsWhitespace() {
        // Given
        String name = "  Charlie  ";

        // When
        HelloDto dto = svc.getHello(name.trim());

        // Then
        assertThat(dto).isNotNull();
        assertThat(dto.getMessage()).isEqualTo("Hello, Charlie!");
    }

    @Test
    @DisplayName("getHello handles very long names")
    void getHello_longName() {
        // Given
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append('a');
        String longName = sb.toString();

        // When
        HelloDto dto = svc.getHello(longName);

        // Then
        assertThat(dto).isNotNull();
        assertThat(dto.getMessage()).startsWith("Hello, ");
        assertThat(dto.getMessage()).endsWith("!");
    }
}
