package primaryAdapters.controllers.plan;

import application.plan.Plan;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlanAssembler implements RepresentationModelAssembler<Plan, EntityModel<Plan>> {
  @Override
  public EntityModel<Plan> toModel(Plan entity) {
    return EntityModel.of(entity,
      linkTo(methodOn(PlanController.class).one(entity.getId().getValue())).withSelfRel(),
      linkTo(methodOn(PlanController.class).all()).withRel("plans")
    );
  }
}
