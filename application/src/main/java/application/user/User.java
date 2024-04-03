package application.user;

import java.util.UUID;

public record User(UserId id, String name, String email, Role role) {
  public User copy(String name, Role role) {
    return new User(this.id, name, this.email, role);
  }

  public User(String name, String email) {
    this(new UserId(UUID.randomUUID()), name, email, Role.USER);
  }
}