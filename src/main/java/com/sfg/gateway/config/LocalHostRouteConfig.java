package com.sfg.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalHostRouteConfig {

    //look at AntPathMatcher javadoc to understand below path regexp
    @Bean
    public RouteLocator localHostRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                    .uri("http://localhost:8080")
                    .id("beer-service"))
                .route(r -> r.path("/api/v1/customers/**") //** means match anything after the specified path
                    .uri("http://localhost:8081")
                    .id("order-service"))
                .route(r -> r.path("/api/v1/beer/*/inventory")
                    .uri("http://localhost:8082")
                    .id("inventory-service"))
                .build();
    }
}
