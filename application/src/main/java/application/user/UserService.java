package application.user;

import java.util.List;

public class UserService {
  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> getAll() {
    return repository.findAll();
  }

  public User getOne(UserId id) {
    return repository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }

  public User update(UserUpdateRequest request, UserId id) {
    return repository.findById(id)
      .map(user -> {
        User copied = user.copy(request.name().orElse(user.name()), request.role().orElse(user.role()));
        return repository.save(copied);
      }).orElseThrow(() -> new UserNotFoundException(id));
  }

  public User create(UserCreateRequest request) {
    return repository.save(new User(request.name(), request.email()));
  }
}
