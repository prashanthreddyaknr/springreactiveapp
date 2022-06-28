package com.spring.learnreactive.fluxandmonotests;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

	/*
	 * @Test public void fluxTest() { // Flux<String> strFlux = Flux.just("Spring",
	 * "Spring Boot", "Reactive Spring"); // strFlux.subscribe(System.out::println);
	 * 
	 * // Flux<String> strFlux = Flux.just("Spring", "Spring Boot",
	 * "Reactive Spring") // .concatWith(Flux.error(new
	 * RuntimeException("Exception occured.")));
	 * 
	 * // Flux<String> strFlux = Flux.just("Spring", "Spring Boot",
	 * "Reactive Spring") // .concatWith(Flux.error(new
	 * RuntimeException("Exception occured."))).log();
	 * 
	 * // Flux<String> strFlux = Flux.just("Spring", "Spring Boot",
	 * "Reactive Spring").log();
	 * 
	 * // Flux<String> strFlux = Flux.just("Spring", "Spring Boot",
	 * "Reactive Spring") // .concatWith(Flux.error(new
	 * RuntimeException("Exception occured."))) //
	 * .concatWith(Flux.just("After error")) // .log(); //
	 * strFlux.subscribe(System.out::println, e ->
	 * System.err.println(e.getMessage()));
	 * 
	 * Flux<String> strFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
	 * // .concatWith(Flux.error(new RuntimeException("Exception occured.")))
	 * .concatWith(Flux.just("After error")) .log();
	 * strFlux.subscribe(System.out::println, e ->
	 * System.err.println(e.getMessage()), () ->
	 * System.out.println("Completed Event")); }
	 * 
	 * 
	 * @Test public void fluxTestElements_withoutError() { Flux<String> strFlux =
	 * Flux.just("Spring", "Spring Boot", "Reactive Spring").log();
	 * 
	 * StepVerifier.create(strFlux).expectNext("Spring").expectNext("Spring Boot").
	 * expectNext("Reactive Spring").verifyComplete(); }
	 * 
	 * @Test public void fluxTestElements_withError() { Flux<String> strFlux =
	 * Flux.just("Spring", "Spring Boot", "Reactive Spring")
	 * .concatWith(Flux.error(new RuntimeException("Exception occured."))) .log();
	 * StepVerifier.create(strFlux) .expectNext("Spring") .expectNext("Spring Boot")
	 * .expectNext("Reactive Spring") // .expectError(RuntimeException.class)
	 * .expectErrorMessage("Exception occured.") .verify(); }
	 * 
	 * @Test public void fluxTestElements_withError1() { Flux<String> strFlux =
	 * Flux.just("Spring", "Spring Boot", "Reactive Spring")
	 * .concatWith(Flux.error(new RuntimeException("Exception occured."))) .log();
	 * StepVerifier.create(strFlux) .expectNext("Spring", "Spring Boot",
	 * "Reactive Spring") // .expectNext("Spring Boot") //
	 * .expectNext("Reactive Spring") // .expectError(RuntimeException.class)
	 * .expectErrorMessage("Exception occured.") .verify(); }
	 * 
	 * 
	 * @Test public void fluxTestElementsCount_withError() { Flux<String> strFlux =
	 * Flux.just("Spring", "Spring Boot", "Reactive Spring")
	 * .concatWith(Flux.error(new RuntimeException("Exception occured."))) .log();
	 * StepVerifier.create(strFlux) .expectNextCount(3) //
	 * .expectNext("Spring Boot") // .expectNext("Reactive Spring") //
	 * .expectError(RuntimeException.class)
	 * .expectErrorMessage("Exception occured.") .verify(); }
	 */

	
	
	@Test
	public void monoTest() {
		Mono<String> monoStr = Mono.just("Spring");
		StepVerifier.create(monoStr.log())
		.expectNext("Spring")
		.verifyComplete();
	}
	
	@Test
	public void monoTest_Error() {
//		Mono<String> monoStr = Mono.just("Spring");
		StepVerifier.create(Mono.error(new RuntimeException("Exception occurred.")).log())
		.expectError(RuntimeException.class)
		.verify();
	}
}
