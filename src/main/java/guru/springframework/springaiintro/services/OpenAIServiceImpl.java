package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author igorg
 * @create 04.05.2024
 */
@Service
public class OpenAIServiceImpl implements OpenAIService {

	private final ChatClient chatClient;

	public OpenAIServiceImpl(final ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@Override
	public Answer getAnswer(final Question question) {
		PromptTemplate promptTemplate = new PromptTemplate(question.question());
		Prompt prompt = promptTemplate.create();
		ChatResponse response = chatClient.call(prompt);

		return new Answer(response.getResult().getOutput().getContent());
	}

	@Override
	public String getAnswer(final String question) {
		PromptTemplate promptTemplate = new PromptTemplate(question);
		Prompt prompt = promptTemplate.create();
		ChatResponse response = chatClient.call(prompt);

		return response.getResult().getOutput().getContent();
	}
}
