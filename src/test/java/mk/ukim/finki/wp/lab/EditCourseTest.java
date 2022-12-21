package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.enums.Type;
import mk.ukim.finki.wp.lab.repository.jpa.JpaCourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.service.impl.CourseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EditCourseTest {

    private CourseService courseService;
    @Mock
    private JpaCourseRepository courseRepository;
    @Mock
    private StudentService studentService;
    @Mock
    private TeacherService teacherService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Course course = new Course();
        course.setCourseId(1L);
        course.setName("Course");
        course.setDescription("Description");
        course.setTeacher(null);
        course.setType(Type.MANDATORY);

        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        courseService = Mockito.spy(new CourseServiceImpl(courseRepository, studentService, teacherService));
    }

    @Test
    public void testEditExistentCourse() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setName("Course");
        course.setDescription("Description");
        course.setTeacher(null);
        course.setType(Type.MANDATORY);

        Course result = courseService.editCourse(1L, "Course", "Description", null, "MANDATORY");
        Mockito.verify(courseService).editCourse(1L, "Course", "Description", null, "MANDATORY");

        Assert.assertEquals(course, result);
    }

    @Test
    public void testEditNonExistentCourse() {
        Course c = courseService.editCourse(2L, "Course", "Description", null, "MANDATORY");
        Mockito.verify(courseService).editCourse(2L, "Course", "Description", null, "MANDATORY");

        Assert.assertNull(c);
    }
}
