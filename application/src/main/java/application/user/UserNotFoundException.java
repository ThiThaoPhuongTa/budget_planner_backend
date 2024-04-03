package application.user;

import application.NotFoundException;

class UserNotFoundException extends NotFoundException {
  UserNotFoundException(UserId id) {
    super("Could not find user " + id.value());
  }
}