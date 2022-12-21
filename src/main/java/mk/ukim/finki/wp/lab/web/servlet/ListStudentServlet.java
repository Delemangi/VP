package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listStudents", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        String parameter = req.getSession().getAttribute("course").toString();
        Course course = courseService.getCourseById(Long.parseLong(parameter));
        List<Student> students = new ArrayList<>(studentService.listAll());
        students.removeAll(course.getStudents());
        webContext.setVariable("students", students);
        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        String parameter = req.getParameter("course");
        req.getSession().setAttribute("course", parameter);

        if (parameter == null) {
            resp.sendRedirect("/courses");
            return;
        }

        Course course = courseService.getCourseById(Long.parseLong(parameter));
        List<Student> students = new ArrayList<>(studentService.listAll());
        students.removeAll(courseService.listStudentsByCourse(course.getCourseId()));
        webContext.setVariable("students", students);
        webContext.setVariable("course", course.getCourseId());
        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
    }
}
