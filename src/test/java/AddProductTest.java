import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddProductTest {
    WebDriver driver;
    
    @BeforeClass(alwaysRun = true)
    public void init() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://localhost:58181/faces/common/signIn.xhtml");
    }
    
    @Test
    public void addProductTest() {
        // Logowanie
        driver.findElement(By.name("j_username")).sendKeys("LRey");
        driver.findElement(By.name("j_password")).sendKeys("P@ssw0rd");
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/p/input"))
                .click();
        
        // Dodanie produktu
        String id = "CreateProductForm:";
        driver.get("https://localhost:58181/faces/product/createNewProduct.xhtml");
        driver.findElement(By.id(id + "productSymbol"))
                .sendKeys("5555555555555");
        driver.findElement(By.id(id + "description"))
                .sendKeys("Opis produktu");
        driver.findElement(By.id(id + "price"))
                .sendKeys("9.99");
        driver.findElement(By.id(id + "weight"))
                .sendKeys("10");
        driver.findElement(By.name(id + "j_idt35")).click();
        
        // Sprawdzenie czy produkt został dodany
        driver.get("https://localhost:58181/faces/product/listProducts.xhtml");
        WebElement locationName = driver.findElement(
                By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[1]/td[2]"));
        Assert.assertEquals(locationName.getText(), "Opis produktu");
        
        // Usunięcie dodanego produktu
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[1]/td[6]/input[2]"))
                .click();
        driver.findElement(By.name("DeleteProductForm:j_idt30")).click();
        
        // Sprawdzenie czy usunięto produkt
        Assert.assertEquals(driver.findElements(By.tagName("tr")).size(), 9);
    }
    
    @AfterClass(alwaysRun = true)
    public void end() {
        driver.close();
    }
}