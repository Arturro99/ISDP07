import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddContractorTest {
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void init() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
        FirefoxBinary bin = new FirefoxBinary();
        bin.addCommandLineOptions("--headless");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(bin);
        driver = new FirefoxDriver(options);
        driver.get("http://localhost:58080/faces/common/signIn.xhtml");
    }

    @Test
    public void addContractorTest() {
        //Logowanie
        Assert.assertTrue(false);
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/"
                + "tbody/tr/td/table/tbody/tr[1]/td[2]/input")).sendKeys("LRey");
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/"
                + "tbody/tr/td/table/tbody/tr[2]/td[2]/input")).sendKeys("P@ssw0rd");
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/p/input")).click();

        //Dodawanie kontrahenta
        driver.get("https://localhost:58181/faces/contractor/registerContractor.xhtml");
        driver.findElement(By.name("RegisterContractorForm:contractorNumber")).sendKeys("1234567891011");
        driver.findElement(By.name("RegisterContractorForm:contractorName")).sendKeys("Unikalna nazwa");
        driver.findElement(By.name("RegisterContractorForm:street")).sendKeys("Zmyslona");
        driver.findElement(By.name("RegisterContractorForm:house")).sendKeys("42");
        driver.findElement(By.name("RegisterContractorForm:zip")).sendKeys("97-300");
        driver.findElement(By.name("RegisterContractorForm:city")).sendKeys("Piotrkow Trybunalski");
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();

        //Sprawdzenie czy nazwa dodanego kontrahenta jest zgodna
        driver.get("https://localhost:58181/faces/contractor/listContractors.xhtml");
        WebElement name = driver.findElement(
                By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[last()]/td[2]"));
        Assert.assertEquals(name.getText(), "Unikalna nazwa");


        //Usunięcie kontrahenta
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/"
                + "tbody/tr[last()]/td[10]/input[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();

        //Sprawdzenie czy kontrahent został usunięty
        name = driver.findElement(
                By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[last()]/td[2]"));
        Assert.assertNotEquals(name.getText(), "Unikalna nazwa");
    }

    @AfterClass(alwaysRun = true)
    public void end() {
        driver.close();
    }
}
