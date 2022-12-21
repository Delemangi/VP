package mk.ukim.finki.wp.lab;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class TestButtonVisibility {
    private HtmlUnitDriver driver;

    @BeforeEach
    public void setup() {
        driver = new HtmlUnitDriver(true);
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @Test
    void testButtonVisibility() {
        driver.get("http://localhost:8080/courses");

        Assert.assertTrue(driver.findElements(By.tagName("button")).isEmpty());

        driver.get("http://localhost:8080/login");
        var loginButton = driver.findElement(By.tagName("button"));
        var username = driver.findElement(By.id("username"));
        var password = driver.findElement(By.id("password"));

        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
        driver.get("http://localhost:8080/courses");

        Assert.assertFalse(driver.findElements(By.tagName("button")).isEmpty());
    }
}
