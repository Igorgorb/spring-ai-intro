package guru.springframework.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * @Author igorg
 * @create 11.05.2024
 */
public record GetCapitalResponse(@JsonPropertyDescription("This is city name") String answer) {
}
