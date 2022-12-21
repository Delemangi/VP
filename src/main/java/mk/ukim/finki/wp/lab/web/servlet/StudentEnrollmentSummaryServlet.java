package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "studentEnrollmentSummary", urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final GradeService gradeService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, GradeService gradeService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        String parameter = req.getParameter("student");
        Long courseID = Long.parseLong(req.getSession().getAttribute("course").toString());
        Course course = courseService.getCourseById(courseID);
        webContext.setVariable("course", course.getName());
        courseService.addStudentInCourse(parameter, courseID);
        List<Student> students = courseService.getCourseById(courseID).getStudents();
        webContext.setVariable("students", students);

        Map<String, Grade> grades = new HashMap<>();
        for (Student student : students) {
            Grade grade = gradeService.findByCourseIdAndStudentUsername(courseID, student.getUsername());
            grades.put(student.getUsername(), grade);
        }

        webContext.setVariable("formatter", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        webContext.setVariable("grades", grades);
        springTemplateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
    }
}
