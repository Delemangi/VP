package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FilterServlet", urlPatterns = "/filter")
public class FilterServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;

    public FilterServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        var courses = courseService.getCourses();
        var students = studentService.listAll();

        webContext.setVariable("courses", courses);
        webContext.setVariable("students", students);

        springTemplateEngine.process("filter.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        var student = req.getParameter("student");
        var course = req.getParameter("course");

        if ((student == null || student.equals("")) && (course == null || course.equals(""))) {
            resp.sendRedirect("/filterStudents");
        } else if (course != null) {
            var students = courseService.listStudentsByCourse(Long.parseLong(course));

            webContext.setVariable("resultStudents", students);

            springTemplateEngine.process("filtered.html", webContext, resp.getWriter());
        } else {
            var student1 = studentService.getByUsername(student);
            var courses = courseService.getCourses()
                    .stream()
                    .filter(c -> c.getStudents().contains(student1))
                    .toList();

            webContext.setVariable("resultCourses", courses);

            springTemplateEngine.process("filtered.html", webContext, resp.getWriter());
        }
    }
}
