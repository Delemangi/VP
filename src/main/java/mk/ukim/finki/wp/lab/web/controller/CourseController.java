package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
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
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long teacher, @RequestParam(required = false) Long course) {
        if (course == null) {
            courseService.addCourse(name, description, teacher);
        } else {
            courseService.editCourse(course, name, description, teacher);
        }

        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteCourse(id);

        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        if (courseService.getCourseById(id) == null) {
            return "redirect:/courses?error=Course Not Found";
        }

        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("teachers", teacherService.findAll());

        return "add-course";
    }

    @GetMapping("/add")
    public String getAddCoursePage(@NotNull Model model) {
        model.addAttribute("teachers", teacherService.findAll());

        return "add-course";
    }
}
