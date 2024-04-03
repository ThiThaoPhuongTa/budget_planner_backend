package primaryAdapters.controllers.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserCreateForm {
  @NotNull
  @Size(min=2, max=30)
  private String name;

  @NotNull
  @Email
  private String email;

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
