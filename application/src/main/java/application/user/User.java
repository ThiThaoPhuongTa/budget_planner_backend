package application.user;

import application.IdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
  @JsonSerialize(using = IdSerializer.class)
  @EmbeddedId
  private UserId id;
  private String name;
  private String email;
  private Role role;

  public User copy(String name, Role role) {
    return new User(this.id, name, this.email, role);
  }

  public User(String name, String email) {
    this(new UserId(UUID.randomUUID()), name, email, Role.USER);
  }

  @Override
  public String toString() {
    return "User{" +
      "userId=" + id +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", role=" + role +
      '}';
  }
}