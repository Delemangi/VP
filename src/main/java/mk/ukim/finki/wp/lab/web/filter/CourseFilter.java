package mk.ukim.finki.wp.lab.web.filter;

import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter
@Profile("servlet")
public class CourseFilter implements Filter {
    private final List<String> allowedPaths = List.of("/listCourses", "/courses", "/courses/delete", "/courses/search");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var req = (HttpServletRequest) servletRequest;
        var resp = (HttpServletResponse) servletResponse;
        String course = req.getParameter("course");
        String sessionCourse = (String) req.getSession().getAttribute("course");

        if (allowedPaths.stream().anyMatch(path -> req.getServletPath().startsWith(path))) {
            filterChain.doFilter(req, resp);
            return;
        }

        if (course == null && sessionCourse == null) {
            resp.sendRedirect("/courses");
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
