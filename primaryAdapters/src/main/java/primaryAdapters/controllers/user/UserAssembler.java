package primaryAdapters.controllers.user;

import application.user.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

record UserResponse(
  UUID id,
  String name,
  String email,
  String role
) {}

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<UserResponse>> {

  @Override
  public EntityModel<UserResponse> toModel(User entity) {
    return EntityModel.of(new UserResponse(entity.id().value(), entity.name(), entity.email(), entity.role().name()),
      linkTo(methodOn(UserController.class).one(entity.id().value())).withSelfRel(),
      linkTo(methodOn(UserController.class).all()).withRel("users")
    );
  }
}