package secondaryAdapters.jpa.user;

import application.user.User;
import application.user.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, UserId> {}