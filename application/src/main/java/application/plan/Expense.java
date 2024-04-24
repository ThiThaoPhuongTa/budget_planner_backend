package application.plan;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Expense {
  @NotNull
  @NotBlank
  private String name;
  @NotNull
  @Positive
  private Long amount;
  @NotNull
  @NotBlank
  private String account;

  @Override
  public String toString() {
    return "Expense{" +
      "name='" + name + '\'' +
      ", amount=" + amount +
      ", account='" + account + '\'' +
      '}';
  }
}