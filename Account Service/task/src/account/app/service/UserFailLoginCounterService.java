package account.app.service;

import account.app.model.UserFailLoginCounter;

/**
 * Roman Pashkov created on 04.10.2022 inside the package - account.app.service
 */
public interface UserFailLoginCounterService {

    void increaseUserLoginFailureCount (UserFailLoginCounter userFailLoginCounter);

    void clearUserLoginFailureCounter (UserFailLoginCounter userFailLoginCounter);

}
