package net.sharplab.epub.konjac.domain.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring ApplicationContext Configuration for domain testing
 */
@Configuration
@Import(DomainConfig.class)
public class TestDomainConfig {

    @Bean
    RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder();
    }
}
