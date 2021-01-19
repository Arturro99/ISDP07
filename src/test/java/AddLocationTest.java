
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
       
        driver.get("https://localhost:58181/faces/common/signIn.xhtml");
        // Logowanie
        driver.findElement(By.name("j_username")).sendKeys("JDoe");
        driver.findElement(By.name("j_password")).sendKeys("P@ssw0rd");
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/p/input")).click();
        
        //Dodanie lokalizacji 
        driver.get("https://localhost:58181/faces/location/createNewLocation.xhtml");
        driver.findElement(By.name("CreateLocationForm:locationSymbol")).sendKeys("YY-00-00-00");
        Select locationType = new Select(
                driver.findElement(By.name("CreateLocationForm:locationType")));
        locationType.selectByVisibleText("CAŁA");
        driver.findElement(By.name("CreateLocationForm:j_idt34")).click();
        
        //Sprawdzenie czy lokalizacja została odpowiednio dodana
        driver.get("https://localhost:58181/faces/location/listLocations.xhtml");
        WebElement locationName = driver.findElement(
                By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[11]/td[1]"));
        Assert.assertEquals(locationName.getText(), "YY-00-00-00");
        
        //Usunięcie dodanej lokalizacji
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[11]/td[5]/input[2]")).click();
        driver.findElement(By.name("DeleteLocationForm:j_idt30")).click();
        
        //Sprawdzenie czy usunięto lokalizację
        Assert.assertEquals(driver.findElements(By.tagName("tr")).size(), 11);
    }
    
    @AfterClass(alwaysRun = true)
    public void end() {
        driver.close();
    }
    
}
