package primaryAdapters.controllers.user;

import application.user.*;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
  private final UserService service;

  private final UserAssembler assembler;

  public UserController(UserService service, UserAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @GetMapping("/users")
  CollectionModel<EntityModel<User>> all() {
    List<EntityModel<User>> users = service.getAll().stream().map(assembler::toModel).toList();

    return CollectionModel.of(users,
      linkTo(methodOn(UserController.class).all()).withSelfRel()
    );
  }

  @GetMapping("/users/{id}")
  EntityModel<User> one(@PathVariable UUID id) {
    return assembler.toModel(service.getOne(new UserId(id)));
  }

  @PostMapping("/users")
  ResponseEntity<?> newUser(@RequestBody @Valid UserCreateRequest request) {
    EntityModel<User> entity = assembler.toModel(service.create(request));

    return ResponseEntity
      .created(entity.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entity);
  }

  @PutMapping("/users/{id}")
  ResponseEntity<?> replaceUser(@RequestBody @Valid UserUpdateRequest request, @PathVariable UUID id) {
    EntityModel<User> entity = assembler.toModel(service.update(request, new UserId(id)));

    return ResponseEntity
      .created(entity.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entity);
  }
}