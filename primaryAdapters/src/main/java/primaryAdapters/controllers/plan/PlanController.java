package primaryAdapters.controllers.plan;

import application.plan.*;
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
@RequestMapping("/plans")
public class PlanController {
  private final PlanService service;

  private final PlanAssembler assembler;

  public PlanController(PlanService service, PlanAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @GetMapping("")
  CollectionModel<EntityModel<Plan>> all() {
    List<EntityModel<Plan>> plans = service.getAll().stream().map(assembler::toModel).toList();

    return CollectionModel.of(plans,
      linkTo(methodOn(PlanController.class).all()).withSelfRel()
    );
  }

  @GetMapping("/{id}")
  EntityModel<Plan> one(@PathVariable UUID id) {
    return assembler.toModel(service.getOne(new PlanId(id)));
  }

  @PostMapping("")
  ResponseEntity<?> newUser(@RequestBody @Valid PlanCreateRequest request) {
    EntityModel<Plan> entity = assembler.toModel(service.create(request));

    return ResponseEntity
      .created(entity.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entity);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replacePlan(@RequestBody @Valid PlanUpdateRequest request, @PathVariable UUID id) {
    EntityModel<Plan> entity = assembler.toModel(service.update(request, new PlanId(id)));

    return ResponseEntity
      .created(entity.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entity);
  }
}