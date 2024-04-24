package application.plan;

import application.IdSerializer;
import application.user.UserId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "plans")
public class Plan {
  @JsonSerialize(using = IdSerializer.class)
  @EmbeddedId
  private PlanId id;
  @Column(name="plan_month")
  private LocalDate month;
  @Embedded
  private Income income;
  @ElementCollection
  private List<Expense> expenses;
  private UserId userId;

  public Plan(PlanId id, LocalDate month, Income income, List<Expense> expenses) {
    this.id = id;
    this.month = month;
    this.income = income;
    this.expenses = expenses;
  }

  public Plan copy(Income income, List<Expense> expenses) {
    return new Plan(this.id, this.month, income, expenses);
  }

  public Plan(LocalDate month, Income income, List<Expense> expenses, UserId userId) {
    this.id = new PlanId(UUID.randomUUID());
    this.month = month;
    this.income = income;
    this.expenses = expenses;
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Plan{" +
      "id=" + id +
      ", month=" + month +
      ", income=" + income +
      ", expenses=" + expenses +
      ", userId=" + userId +
      '}';
  }
}