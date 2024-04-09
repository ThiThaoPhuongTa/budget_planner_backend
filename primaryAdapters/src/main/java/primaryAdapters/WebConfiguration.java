package primaryAdapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {
  @Bean
  public WebMvcConfigurer corsConfigure() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:3000");
      }
    };
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(a ->
        a.requestMatchers("/oauth2/authorization/google", "/login/oauth2/code/*", "/").permitAll()
          .anyRequest().authenticated()
      )
      .exceptionHandling(e ->
        e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
      )
      .oauth2Login(oauth ->
        oauth.loginProcessingUrl("/oauth2/authorization/google")
          .redirectionEndpoint(redirection -> redirection
            .baseUri("/login/oauth2/code/*")
          )
      )
      .logout(l ->
        l.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
          .logoutSuccessUrl("/")
      );

    return http.build();
  }
}