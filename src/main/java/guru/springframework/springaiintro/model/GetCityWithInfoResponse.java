package guru.springframework.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * @Author igorg
 * @create 11.05.2024
 */
public record GetCityWithInfoResponse(
	@JsonPropertyDescription("This is city name") String answer,
	@JsonPropertyDescription("The city has a population of") String population,
	@JsonPropertyDescription("The city is located in") String region,
	@JsonPropertyDescription("The primary language spoken is") String language,
	@JsonPropertyDescription("The currency used is") String currency
) {
}