package application.user;

import application.IdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
  private UserId userId;
  private String name;
  String email;
  Role role;

  public User copy(String name, Role role) {
    return new User(this.userId, name, this.email, role);
  }

  public User(String name, String email) {
    this(new UserId(UUID.randomUUID()), name, email, Role.USER);
  }
}