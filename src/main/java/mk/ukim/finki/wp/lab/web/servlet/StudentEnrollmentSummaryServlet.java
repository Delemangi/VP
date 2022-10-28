package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "studentEnrollmentSummary", urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        String parameter = req.getParameter("student");
        Long courseID = Long.parseLong(req.getSession().getAttribute("course").toString());
        Course course = courseService.getCourseById(courseID);
        webContext.setVariable("course", course.getName());
        courseService.addStudentInCourse(parameter, courseID);
        List<Student> students = courseService.getCourseById(courseID).getStudents();
        webContext.setVariable("students", students);
        springTemplateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
    }
}
