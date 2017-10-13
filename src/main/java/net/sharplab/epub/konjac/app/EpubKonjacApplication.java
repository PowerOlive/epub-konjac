package net.sharplab.epub.konjac.app;

import net.sharplab.epub.konjac.app.config.AppConfig;
import net.sharplab.epub.konjac.domain.config.DomainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {AppConfig.class, DomainConfig.class})
public class EpubKonjacApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(EpubKonjacApplication.class);
		springApplication.setAddCommandLineProperties(false);
		springApplication.run(args);
	}
}
