package application.plan;

import application.NotFoundException;

class PlanNotFoundException extends NotFoundException {
  PlanNotFoundException(PlanId id) {
    super("Could not find plan " + id.getValue());
  }
}