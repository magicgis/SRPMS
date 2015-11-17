package ctrl;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guofan on 2015/9/23.
 */
public class SecurityFilter implements HandlerInterceptor {
    //    @Autowired
//    protected HttpServletRequest request;
    Long Start;
    Long End;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Start = System.currentTimeMillis();
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            httpServletResponse.sendRedirect("/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        End = System.currentTimeMillis();
        System.out.println(End - Start);
    }
}
