package application.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
  @NotNull @Size(min = 2, max = 20) String name,
  @NotNull @Email String email
) {}