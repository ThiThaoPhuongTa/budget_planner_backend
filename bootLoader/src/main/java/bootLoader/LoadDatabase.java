package bootLoader;

import application.user.UserCreateRequest;
import application.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase {
  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner innitDatabase(UserService service) {
    return args -> {
      log.info("Preloading " + service.create(new UserCreateRequest("phuong", "phuong@gmail.com")));
      log.info("Preloading " + service.create(new UserCreateRequest("thao", "thao@gmail.com")));
    };
  }
}