package primaryAdapters.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AuthController {
  @GetMapping("/me")
  public Map<String, Object> me(@AuthenticationPrincipal OAuth2User principal) {
      return Collections.singletonMap("name", principal.getAttribute("name"));
  }
}