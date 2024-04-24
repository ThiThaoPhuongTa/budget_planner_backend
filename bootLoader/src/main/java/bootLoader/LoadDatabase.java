package bootLoader;

import application.plan.Expense;
import application.plan.Income;
import application.plan.PlanCreateRequest;
import application.plan.PlanService;
import application.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@Configuration
public class LoadDatabase {
  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner innitUsers(UserService service) {
    return args -> log.info("Preloading " + service.create(new User(
        new UserId(UUID.fromString("932ec4e1-99d8-4215-a321-03615f6cd2d4")),
        "phuong",
        "phuong@gmail.com",
        Role.ADMIN
      )));
  }

  @Bean
  CommandLineRunner innitPlans(PlanService service) {
    return args -> log.info("Preloading " + service.create(new PlanCreateRequest(
        LocalDate.of(2024, 1, 1),
        new Income(10000000L),
        List.of(new Expense("Food", 5000000L, "Cash")),
        new UserId(UUID.fromString("932ec4e1-99d8-4215-a321-03615f6cd2d4"))
      )));
  }
}