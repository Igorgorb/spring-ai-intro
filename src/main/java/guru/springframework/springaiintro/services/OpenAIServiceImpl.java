package guru.springframework.springaiintro.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

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

	@Value("classpath:templates/get-capital-prompt.st")
	private Resource getCapitalPrompt;

	@Value("classpath:templates/get-capital-with-info.st")
	private Resource getGetCapitalWithInfoPrompt;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Answer getCapitalWithInfo(final GetCapitalRequest getCapitalRequest) {
		PromptTemplate promptTemplate = new PromptTemplate(getGetCapitalWithInfoPrompt);
		Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
		ChatResponse response = chatClient.call(prompt);

		return new Answer(response.getResult().getOutput().getContent());
	}

	@Override
	public Answer getCapital(final GetCapitalRequest getCapitalRequest) {
		PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
		Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
		ChatResponse response = chatClient.call(prompt);

		System.out.println(response.getResult().getOutput().getContent());
		String responseString;
		try {
			JsonNode jsonNode = objectMapper.readTree(response.getResult().getOutput().getContent());
			responseString = jsonNode.get("answer").asText();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return new Answer(responseString);
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
