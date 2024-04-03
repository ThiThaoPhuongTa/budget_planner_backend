package primaryAdapters.controllers.user;

import application.user.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
  private final UserService service;

  private final UserAssembler assembler;

  @Autowired
  public UserController(UserService service, UserAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @GetMapping("/users")
  CollectionModel<EntityModel<UserResponse>> all() {
    List<EntityModel<UserResponse>> users = service.getAll().stream().map(assembler::toModel).toList();

    return CollectionModel.of(users,
      linkTo(methodOn(UserController.class).all()).withSelfRel()
    );
  }

  @GetMapping("/users/{id}")
  EntityModel<UserResponse> one(@PathVariable UUID id) {
    return assembler.toModel(service.getOne(new UserId(id)));
  }

  @PostMapping("/users")
  ResponseEntity<?> newUser(@RequestBody @Valid UserCreateForm form) {
    UserCreateRequest request = new UserCreateRequest(form.getName(), form.getEmail());
    EntityModel<UserResponse> entity = assembler.toModel(service.create(request));

    return ResponseEntity
      .created(entity.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entity);
  }

  @PutMapping("/users/{id}")
  ResponseEntity<?> replaceUser(@RequestBody @Valid UserUpdateForm form, @PathVariable UUID id) {
    UserUpdateRequest request = new UserUpdateRequest(
      Optional.ofNullable(form.getName()),
      Optional.ofNullable(form.getRole()).map(Role::valueOf)
    );
    EntityModel<UserResponse> entity = assembler.toModel(service.update(request, new UserId(id)));

    return ResponseEntity
      .created(entity.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entity);
  }
}