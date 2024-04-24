package secondaryAdapters.jpa.plan;

import application.plan.Plan;
import application.plan.PlanId;
import application.plan.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanRepositoryOnJpa implements PlanRepository {
  private final SpringDataPlanRepository repository;

  public PlanRepositoryOnJpa(SpringDataPlanRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Plan> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Plan> findById(PlanId id) {
    return repository.findById(id);
  }

  @Override
  public Plan save(Plan plan) {
    repository.save(plan);
    return plan;
  }
}