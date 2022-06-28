package com.spring.learnreactive.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.learnreactive.handler.SimpleHanlerFunction;

@Configuration
public class RouterFunctionConfig {
	
	@Bean
	public RouterFunction<ServerResponse> route(SimpleHanlerFunction simpleHanlerFunction) {
		
		return RouterFunctions
				.route(GET("/funtional/flux").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), simpleHanlerFunction::returnFlux)
				.andRoute(GET("/funtional/mono").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), simpleHanlerFunction::returnMono);
		
	}

}
