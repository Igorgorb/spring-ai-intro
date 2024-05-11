package guru.springframework.springaiintro.controllers;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.GetCapitalResponse;
import guru.springframework.springaiintro.model.GetCityWithInfoResponse;
import guru.springframework.springaiintro.model.Question;
import guru.springframework.springaiintro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author igorg
 * @create 06.05.2024
 */
@RestController
public class QuestionController {

	private final OpenAIService openAIService;

	public QuestionController(final OpenAIService openAIService) {
		this.openAIService = openAIService;
	}

	@PostMapping("/capitalWithInfo")
	public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest){
		return this.openAIService.getCapitalWithInfo(getCapitalRequest);
	}

	@PostMapping("/cityWithInfo")
	public GetCityWithInfoResponse getCityWithInfo(@RequestBody GetCapitalRequest getCapitalRequest){
		return this.openAIService.getCityWithInfo(getCapitalRequest);
	}

	@PostMapping("/capital")
	public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
		return this.openAIService.getCapital(getCapitalRequest);
	}

	@PostMapping("/ask")
	public Answer askQuestion(@RequestBody Question question){
		return openAIService.getAnswer(question);
	}
}
