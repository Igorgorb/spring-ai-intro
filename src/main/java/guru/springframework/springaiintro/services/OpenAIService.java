package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Question;

/**
 * @Author igorg
 * @create 04.05.2024
 */
public interface OpenAIService {
	String getAnswer(String question);

	Answer getAnswer(Question question);
}
