package secondaryAdapters.jpa.user;

import application.user.User;
import application.user.UserId;
import application.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepositoryOnJpa implements UserRepository {
  private final SpringDataUserRepository repository;

  @Autowired
  public UserRepositoryOnJpa(SpringDataUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<User> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<User> findById(UserId id) {
    return repository.findById(id);
  }

  @Override
  public User save(User user) {
    repository.save(user);
    return user;
  }
}