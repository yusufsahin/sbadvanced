package tr.com.provera.pameraapi.config;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tr.com.provera.pameraapi.audit.AuditorAwareImpl;

import org.springframework.data.domain.AuditorAware;



@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EntityScan(basePackages = {"tr.com.provera.pameraapi.dao.model"})
@EnableJpaRepositories(basePackages = "tr.com.provera.pameraapi.dao")
public class PersistenceConfig {
    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}