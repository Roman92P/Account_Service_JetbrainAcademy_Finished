package account.app.ropository;

import account.app.model.UserFailLoginCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Roman Pashkov created on 04.10.2022 inside the package - account.app.ropository
 */
@Repository
public interface UserFailLoginCounterRepo extends JpaRepository<UserFailLoginCounter, Long> {
}
