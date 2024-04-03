package secondaryAdapters.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "secondaryAdapters.jpa")
@EntityScan(basePackages = "secondaryAdapters.jpa")
public class JpaConfiguration {}