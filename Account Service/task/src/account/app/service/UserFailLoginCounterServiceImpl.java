package account.app.service;

import account.app.model.UserFailLoginCounter;
import account.app.ropository.UserFailLoginCounterRepo;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * Roman Pashkov created on 04.10.2022 inside the package - account.app.service
 */
@Service
public class UserFailLoginCounterServiceImpl implements UserFailLoginCounterService{

    private final UserFailLoginCounterRepo userFailLoginCounterRepo;

    public UserFailLoginCounterServiceImpl(UserFailLoginCounterRepo userFailLoginCounterRepo) {
        this.userFailLoginCounterRepo = userFailLoginCounterRepo;
    }

    @Override
    public void increaseUserLoginFailureCount(UserFailLoginCounter userFailLoginCounter) {
        if (userFailLoginCounterRepo.exists(Example.of(userFailLoginCounter))) {
            userFailLoginCounterRepo.findOne(Example.of(userFailLoginCounter));
        } else {
            userFailLoginCounterRepo.save(userFailLoginCounter);
        }
    }

    @Override
    public void clearUserLoginFailureCounter(UserFailLoginCounter userFailLoginCounter) {

    }
}
