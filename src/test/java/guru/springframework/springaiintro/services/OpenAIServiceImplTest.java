package guru.springframework.springaiintro.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author igorg
 * @create 04.05.2024
 */
@SpringBootTest
class OpenAIServiceImplTest {

	@Autowired
	OpenAIService openAIService;

	@Test
	void getAnswer() throws Exception {
		String answer = openAIService.getAnswer("Write the game snake in python.");
		System.out.println("Got the answer");
		System.out.println(answer);
	}
}