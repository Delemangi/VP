package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private static final List<String> COURSE_TYPES = List.of("WINTER", "SUMMER", "MANDATORY", "ELECTIVE");
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GradeService gradeService;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService, GradeService gradeService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("courses", courseService.getCourses().stream().sorted().toList());

        return "listCourses";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long teacher, @RequestParam String type, @RequestParam(required = false) Long course) {
        if (course == null) {
            courseService.addCourse(name, description, teacher, type);
        } else {
            courseService.editCourse(course, name, description, teacher, type);
        }

        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        List<Grade> grades = gradeService.findByCourseId(id);
        gradeService.deleteAll(grades);
        courseService.deleteCourse(id);

        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        if (courseService.getCourseById(id) == null) {
            return "redirect:/courses?error=Course Not Found";
        }

        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("types", COURSE_TYPES);

        return "add-course";
    }

    @GetMapping("/add")
    public String getAddCoursePage(@NotNull Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("types", COURSE_TYPES);

        return "add-course";
    }

    @GetMapping("/search")
    public String searchEverything() {
        return "search";
    }

    @PostMapping("/search")
    public String searchResults(@RequestParam String term, Model model) {
        model.addAttribute("courses", courseService.search(term));
        model.addAttribute("teachers", teacherService.search(term));
        model.addAttribute("students", studentService.search(term));
        model.addAttribute("term", term);

        return "search";
    }

    @GetMapping("/addGrade")
    public String getAddGradePage(@NotNull Model model) {
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("students", studentService.findAll());

        return "add-grade";
    }

    @PostMapping("/addGrade")
    public String saveGrade(@RequestParam Long course, @RequestParam String student, @RequestParam Character grade, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        if (course == null || student == null || grade == null || date == null) {
            return "redirect:/courses?error=Invalid Data";
        }

        Student s = studentService.findByUsername(student);
        Course c = courseService.findByCourseId(course);
        Grade g = new Grade(grade, s, c, date);

        gradeService.save(g);

        return "redirect:/courses";
    }
}
