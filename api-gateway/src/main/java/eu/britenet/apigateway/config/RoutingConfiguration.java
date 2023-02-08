package eu.britenet.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfiguration {

//    @Bean
//    RouteLocator gateway(RouteLocatorBuilder rlb){
//        return rlb
//                .routes()
//                .route("mongo-demo-routing", routeSpec -> routeSpec
//                        .query("id")
//                        .and()
//                        .path("/mongo-demo")
//                        .uri("lb://mongo-demo/"))
//                .build();
//    }
}
