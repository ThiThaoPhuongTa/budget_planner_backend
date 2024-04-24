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
        String name = request.name() == null ? user.getName() : request.name();
        Role role = request.role() == null ? user.getRole() : Role.valueOf(request.role());
        User copied = user.copy(name, role);
        return repository.save(copied);
      }).orElseThrow(() -> new UserNotFoundException(id));
  }

  public User create(UserCreateRequest request) {
    return repository.save(new User(request.name(), request.email()));
  }

  public User create(User user) {
    return repository.save(user);
  }
}