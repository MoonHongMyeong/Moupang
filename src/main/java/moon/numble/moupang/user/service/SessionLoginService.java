package moon.numble.moupang.user.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.SessionUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{
    private final HttpSession httpSession;
    @Override
    public void login(SessionUser user) {
        httpSession.setAttribute("USER", user);
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }
}
