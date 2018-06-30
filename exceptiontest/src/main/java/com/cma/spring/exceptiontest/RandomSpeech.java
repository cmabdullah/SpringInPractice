package com.cma.spring.exceptiontest;

import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class RandomSpeech {
	private static String[] texts = {
			"I will back",
			"Get out !",
			"I want you clothes, boots and byck",
			null
	};
	public String getText() {
		Random random = new Random();
		return texts[random.nextInt(texts.length)];
	}
}
