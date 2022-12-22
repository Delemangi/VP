package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

    @BeforeEach
    public void setup() {
        driver = new HtmlUnitDriver(true);
        init();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    public void init() {
        teacherService.save("John", "Doe");
        courseService.save("C1", "C1", 1L, -1L, "WINTER");
    }

    @Test
    void testButtonVisibility() {
        driver.get("http://localhost:8081/courses");

        Assert.assertTrue(driver.findElements(By.tagName("button")).isEmpty());

        driver.get("http://localhost:8081/login");
        var loginButton = driver.findElement(By.tagName("button"));
        var username = driver.findElement(By.id("username"));
        var password = driver.findElement(By.id("password"));

        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
        driver.get("http://localhost:8081/courses");

        Assert.assertFalse(driver.findElements(By.tagName("button")).isEmpty());
    }
}
