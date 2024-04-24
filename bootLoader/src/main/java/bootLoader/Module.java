package bootLoader;

import application.plan.PlanRepository;
import application.plan.PlanService;
import application.user.UserRepository;
import application.user.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"secondaryAdapters", "primaryAdapters"})
public class Module {

  @Bean
  UserService userService(UserRepository repository) {
    return new UserService(repository);
  }
  @Bean
  PlanService planService(PlanRepository repository) {
    return new PlanService(repository);
  }
}
