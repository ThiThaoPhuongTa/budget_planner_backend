package application.plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {
  List<Plan> findAll();

  Optional<Plan> findById(PlanId id);

  Plan save(Plan plan);
}
