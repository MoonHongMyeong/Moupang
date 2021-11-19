package moon.numble.moupang.user.service;

import moon.numble.moupang.common.SessionUser;

public interface LoginService {
    void login(SessionUser user);
    void logout();
}
