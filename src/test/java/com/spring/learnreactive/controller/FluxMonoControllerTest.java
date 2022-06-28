package com.spring.learnreactive.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxMonoControllerTest {

	@Autowired
	WebTestClient webTestClient;
	

	@Test
	public void flux_approach1() {
		
		Flux<Integer> integerFlux = webTestClient.get().uri("/flux")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.returnResult(Integer.class)
		.getResponseBody();
		
		StepVerifier.create(integerFlux)
		.expectSubscription()
		.expectNext(1)
		.expectNext(2)
		.expectNext(3)
		.expectNext(4)
		.verifyComplete();
	}
	
	@Test
	public void flux_approach2() {
		
	 webTestClient.get().uri("/flux")
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Integer.class)
		.hasSize(4);

	}
	
	
	@Test
	public void flux_approach3() {
		
		List<Integer> expectedResult = Arrays.asList(1, 2, 3, 4);
		
		EntityExchangeResult<List<Integer>> results = webTestClient.get().uri("/flux")
	    .accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Integer.class)
		.returnResult();

		assertEquals(expectedResult, results.getResponseBody());
	}
	
	
	@Test
	public void flux_approach4() {
		List<Integer> expectedResult = Arrays.asList(1, 2, 3, 4);
		
		webTestClient.get().uri("/flux")
//		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectBodyList(Integer.class)
		.consumeWith(res -> {
			assertEquals(expectedResult, res.getResponseBody());
		});
	}
	
	@Test
	public void fluxCntStream() {
		
		Flux<Long> longFluxStream = webTestClient.get().uri("/fluxcntstream")
		.accept(MediaType.APPLICATION_STREAM_JSON)
		.exchange()
		.expectStatus().isOk()
		.returnResult(Long.class)
		.getResponseBody();
		
		StepVerifier.create(longFluxStream)
		.expectNext(0l)
		.expectNext(1l)
		.expectNext(2l)
		.expectNext(3l)
		.thenCancel()
		.verify();
		
	}
	
	
	@Test
	public void mono_apporach1() {
		
		Integer expectedValue = Integer.valueOf(1);
		
		webTestClient.get().uri("/mono")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Integer.class)
		.consumeWith(result -> {
			assertEquals(expectedValue, result.getResponseBody());
		});
		
		
		
	}
	
}
