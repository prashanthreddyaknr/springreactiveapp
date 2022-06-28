package com.spring.learnreactive.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class SimpleHandlerFunctionTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	public void fluxTest() {
		
		Flux<Integer> fluxInt = webTestClient.get().uri("/funtional/flux")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.returnResult(Integer.class)
		.getResponseBody();
		
		StepVerifier.create(fluxInt)
		.expectNext(1)
		.expectNext(2)
		.expectNext(3)
		.expectNext(4)
		.verifyComplete();
	}
	
	
	@Test
	public void monoTest() {
		
		Integer expectRes = Integer.valueOf(1);
		
	   webTestClient.get().uri("/funtional/mono")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Integer.class)
		.consumeWith(res -> {
			assertEquals(expectRes, res.getResponseBody());
		});
		
	}

}
