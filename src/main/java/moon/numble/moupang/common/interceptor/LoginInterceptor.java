package moon.numble.moupang.common.interceptor;

import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.user.annotation.LoginRequired;
import moon.numble.moupang.user.exception.UnauthorizedAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final String USER = "USER";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        SessionUser user = (SessionUser) request.getSession().getAttribute(USER);

        if(handlerMethod.hasMethodAnnotation(LoginRequired.class) && user == null){
            throw new UnauthorizedAccessException(USER+" Unauthorized Access");
        }

        return true;
    }

}
