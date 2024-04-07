package application.user;

import application.ValueOfEnum;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
  @Size(min = 2, max = 20)
  String name,

  @ValueOfEnum(enumClass = Role.class)
  String role
) {}