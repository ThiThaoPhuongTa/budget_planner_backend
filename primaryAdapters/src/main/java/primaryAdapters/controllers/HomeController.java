package primaryAdapters.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/")
  @ResponseBody
  public ResponseEntity<?> healthcheck() {
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"status\": \"ok\"}");
  }
}