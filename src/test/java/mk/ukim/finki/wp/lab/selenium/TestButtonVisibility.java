package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class TestButtonVisibility {
    private HtmlUnitDriver driver;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void setup() {
        driver = new HtmlUnitDriver(true);
        init();
    }

    @AfterEach
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }

    public void init() {
        teacherService.save("John", "Doe");
        courseService.save("C1", "C1", 1L, -1L, "WINTER");
        userService.register("admin", "admin", "ROLE_ADMIN");
    }

    @Test
    void testButtonVisibility() {
        CoursesPage coursesPage = CoursesPage.open(driver);
        coursesPage.assertButtonCountsAre(0, 0);

        LoginPage loginPage = LoginPage.open(driver);
        LoginPage.login(driver, loginPage, "admin", "admin");

        coursesPage = CoursesPage.open(driver);
        coursesPage.assertButtonsExist();
    }
}
