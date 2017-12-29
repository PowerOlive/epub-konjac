package net.sharplab.epub.konjac.domain.config;

import net.sharplab.epub.konjac.domain.repository.MSTranslatorRestRepository;
import net.sharplab.epub.konjac.domain.repository.MSTranslatorRestRepositoryImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * Spring ApplicationContext Configuration for domain
 */
@Configuration
@ComponentScan("net.sharplab.epub.konjac.domain.config")
@ComponentScan("net.sharplab.epub.konjac.domain.service")
@ComponentScan("net.sharplab.epub.konjac.domain.repository")
@ComponentScan("net.sharplab.epub.konjac.domain.provider")
public class DomainConfig {

    @Bean
    public MSTranslatorRestRepository msTranslatorRestRepository(RestTemplateBuilder restTemplateBuilder){
        return new MSTranslatorRestRepositoryImpl(restTemplateBuilder);
    }

}
