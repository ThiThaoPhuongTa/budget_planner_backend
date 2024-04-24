package application.plan;

import java.util.List;

public record PlanUpdateRequest(
  Income income,
  List<Expense> expenses
) {}