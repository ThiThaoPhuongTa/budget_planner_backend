package primaryAdapters.controllers.user;

import application.user.Role;
import jakarta.validation.constraints.Size;
import primaryAdapters.ValueOfEnum;

public class UserUpdateForm {
  @Size(min = 2, max = 30)
  private String name;

  @ValueOfEnum(enumClass = Role.class, message = "Must be a role")
  private String role;

  public String getName() {
    return name;
  }

  public String getRole() {
    return role;
  }
}
