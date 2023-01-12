package mk.ukim.finki.wp.lab.selenium;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CoursesPage extends AbstractPage {
    @FindBy(css = ".edit")
    private List<WebElement> editButtons;
    @FindBy(css = ".delete")
    private List<WebElement> deleteButtons;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public static CoursesPage open(WebDriver driver) {
        get(driver, "/courses");
        return PageFactory.initElements(driver, CoursesPage.class);
    }

    public void assertButtonCountsAre(int editCount, int deleteCount) {
        Assert.assertEquals("edit buttons test", editCount, this.editButtons.size());
        Assert.assertEquals("delete buttons test", deleteCount, this.deleteButtons.size());
    }

    public void assertButtonsExist() {
        Assert.assertTrue("edit buttons exist", this.editButtons.size() > 0);
        Assert.assertTrue("delete buttons exist", this.deleteButtons.size() > 0);
    }
}
