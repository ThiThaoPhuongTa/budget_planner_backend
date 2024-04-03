package secondaryAdapters.jpa.user;

import application.user.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserRecord {
  @Id
  private UUID id;
  private String name;
  private String email;
  private Role role;

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Role getRole() {
    return role;
  }

  public UserRecord() {}

  public UserRecord(UUID id, String name, String email, Role role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = role;
  }
}