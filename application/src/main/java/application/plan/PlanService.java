package application.plan;

import java.util.List;

public class PlanService {
  private final PlanRepository repository;

  public PlanService(PlanRepository repository) {
    this.repository = repository;
  }

  public List<Plan> getAll() {
    return repository.findAll();
  }

  public Plan getOne(PlanId id) {
    return repository.findById(id)
      .orElseThrow(() -> new PlanNotFoundException(id));
  }

  public Plan update(PlanUpdateRequest request, PlanId id) {
    return repository.findById(id)
      .map(plan -> {
        Income income = request.income() == null ? plan.getIncome() : request.income();
        List<Expense> expenses = request.expenses() == null ? plan.getExpenses() : request.expenses();
        Plan copied = plan.copy(income, expenses);
        return repository.save(copied);
      }).orElseThrow(() -> new PlanNotFoundException(id));
  }

  public Plan create(PlanCreateRequest request) {
    return repository.save(new Plan(request.month(), request.income(), request.expenses(), request.userId()));
  }
}