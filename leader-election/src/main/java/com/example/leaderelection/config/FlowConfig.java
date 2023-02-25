package com.example.leaderelection.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.file.dsl.Files;

import java.io.File;

@Configuration
@EnableIntegration
@Slf4j
public class FlowConfig {

    @Bean
    public IntegrationFlow filesProcessor() {
        return IntegrationFlow
                .from(Files.inboundAdapter(new File("./input")).autoCreateDirectory(true),
                        pm -> pm.poller(p -> p.fixedDelay(1000))
                                .role("fileReader")
                                .autoStartup(false)
                                )
                .handle(message -> log.info("meesage.getPayload() = " + message.getPayload()))
                .get();
    }
}
