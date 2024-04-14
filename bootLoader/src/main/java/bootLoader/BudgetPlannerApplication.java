package bootLoader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BudgetPlannerApplication {
  public static void main(String[] args) {
    SpringApplication.run(BudgetPlannerApplication.class, args);
  }

  @Bean
  WebMvcConfigurer corsConfigure() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:5173")
          .allowCredentials(true);
      }
    };
  }
}