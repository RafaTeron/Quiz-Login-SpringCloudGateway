package com.rafaelTeron.gateway_service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    private final WebClient.Builder webClientBuilder;

    public GatewayConfig(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("LoginJWT", r -> r.path("/auth/**")
                .uri("http://localhost:8002"))
            .route("app-quiz", r -> r.path("/app-quiz/**") 
                .filters(f -> f.filter((exchange, chain) -> {

                	String path = exchange.getRequest().getURI().getPath();
                    if (path.equals("/app-quiz/quiz/players/register" )) {
                        return chain.filter(exchange);
                    }
                    String authToken = exchange.getRequest().getHeaders().getFirst("Authorization");
                    
                    return validateToken(authToken)
                            .flatMap(isValid -> {
                                if (isValid) {
                                    return chain.filter(exchange); 
                                } else {

                                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                    return exchange.getResponse().setComplete();
                                }
                            });
                }))
                .uri("http://localhost:8001")) 
            .build();
    }

    private Mono<Boolean> validateToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return webClientBuilder.build()
                .get()
                .uri("http://localhost:8002/auth/validate-token")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Boolean.class);
        }
        return Mono.just(false);
    }
    
}