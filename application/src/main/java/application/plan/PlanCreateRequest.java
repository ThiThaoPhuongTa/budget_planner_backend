package application.plan;

import application.user.UserId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record PlanCreateRequest(
  @NotNull
  LocalDate month,
  @Positive
  @NotNull
  Income income,
  List<Expense> expenses,
  UserId userId
) {}
