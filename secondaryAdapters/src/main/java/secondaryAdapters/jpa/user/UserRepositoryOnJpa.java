package secondaryAdapters.jpa.user;

import application.user.User;
import application.user.UserId;
import application.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRepositoryOnJpa implements UserRepository {
  private final SpringDataUserRepository repository;

  @Autowired
  public UserRepositoryOnJpa(SpringDataUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<User> findAll() {
    return repository.findAll().stream().map(UserRepositoryOnJpa::toModel).collect(Collectors.toList());
  }

  @Override
  public Optional<User> findById(UserId id) {
    return repository.findById(id.value()).map(UserRepositoryOnJpa::toModel);
  }

  @Override
  public User save(User user) {
    repository.save(toRecord(user));
    return user;
  }

  private static User toModel(UserRecord record) {
    return new User(new UserId(record.getId()), record.getName(), record.getEmail(), record.getRole());
  }

  private static UserRecord toRecord(User user) {
    return new UserRecord(user.id().value(), user.name(), user.email(), user.role());
  }
}