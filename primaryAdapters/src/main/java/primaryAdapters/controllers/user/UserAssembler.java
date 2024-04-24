package primaryAdapters.controllers.user;

import application.user.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

  @SuppressWarnings("null")
  @Override
  public EntityModel<User> toModel(User entity) {
    return EntityModel.of(entity,
      linkTo(methodOn(UserController.class).one(entity.getId().getValue())).withSelfRel(),
      linkTo(methodOn(UserController.class).all()).withRel("users")
    );
  }
}