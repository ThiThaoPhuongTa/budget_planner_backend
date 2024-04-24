package secondaryAdapters.jpa.plan;

import application.plan.Plan;
import application.plan.PlanId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPlanRepository extends JpaRepository<Plan, PlanId> {}
