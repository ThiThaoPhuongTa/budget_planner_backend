package application.user;

import java.util.Optional;

public record UserUpdateRequest(Optional<String> name, Optional<Role> role) {}