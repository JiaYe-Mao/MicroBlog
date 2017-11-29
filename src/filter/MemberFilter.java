package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MemberFilter", urlPatterns = {"/delete", "/logout", "/message", "/member.jsp"},
            initParams = {@WebInitParam(name="LOGIN_VIEW", value = "index.jsp")})
public class MemberFilter implements Filter {
    private String LOGIN_VIEW;
    public void init(FilterConfig config) throws ServletException {
        this.LOGIN_VIEW = config.getInitParameter("LOGIN_VIEW");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        if (request.getSession().getAttribute("username") != null){
            chain.doFilter(req, resp);
        }else {
            HttpServletResponse response = (HttpServletResponse)resp;
            response.sendRedirect(LOGIN_VIEW);
        }

    }

    public void destroy() {
    }
}
