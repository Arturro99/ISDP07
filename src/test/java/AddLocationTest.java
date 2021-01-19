
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddLocationTest {
    WebDriver driver;
    
    @BeforeClass(alwaysRun = true)
    public void init() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://localhost:58080");
    }
    
    @Test
    public void addLocationTest() {
        Assert.assertNotNull(driver);
        //Assert.assertTrue(false);
    }
}
