package primaryAdapters;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class AuthConfiguration {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(a ->
        a.requestMatchers("/oauth2/authorization/google", "/login/oauth2/code/*", "/").permitAll()
          .anyRequest().authenticated()
      )
      .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
      .csrf((csrf) -> csrf
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
      )
      .oauth2Login(oauth ->
        oauth.loginProcessingUrl("/oauth2/authorization/google")
          .redirectionEndpoint(redirection -> redirection
            .baseUri("/login/oauth2/code/*")
          )
          .defaultSuccessUrl("/redirect")
      )
      .logout(l ->
        l.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
          .deleteCookies("JSESSIONID")
          .logoutSuccessHandler(((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)))
      );

    return http.build();
  }
}