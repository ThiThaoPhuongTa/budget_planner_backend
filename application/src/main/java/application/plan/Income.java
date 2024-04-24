package application.plan;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Income {
  @NotNull
  @Positive
  private Long wage;
  @Column(name = "other_income")
  @Positive
  private Long other;

  public Income(@NotNull Long wage) {
    this.wage = wage;
  }

  @Override
  public String toString() {
    return "Income{" +
      "wage=" + wage +
      ", other=" + other +
      '}';
  }
}