
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class NewUserTest {
    private WebDriver driver;
    private String userName;
    private String userPassword;
    private String loginUrl;
    private String registrationUrl;
    private String clientListUrl;
    
    private String newName;
    private String newSurname;
    private String newEmail;
    private String newLogin;
    private String newPassword;
    private String newQuestion;
    private String newAnswer;
    
    @BeforeTest
    public void init() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/home/student/geckodriver");
        driver = new FirefoxDriver();
        Thread.sleep(3000);
        
        loginUrl = "https://localhost:18181/faces/common/signIn.xhtml";
        registrationUrl = "http://localhost:18080/faces/common/registerAccount.xhtml";
        clientListUrl = "https://localhost:18181/faces/account/listNewAccounts.xhtml";
        
        userName = "DMitchell";
        userPassword = "P@ssw0rd";
        newName = "Janusz";
        newSurname = "Kowalski";
        newEmail = "isdp07@protonmail.com";
        newLogin = "JKowalski";
        newPassword = "P@ssw0rd";
        newQuestion = "Jak to jest być skrybą?";
        newAnswer = "Dobrze.";
    }
    
    @Test
    public void addNewUserTest() {
        //Log in
        driver.get(loginUrl);
        
        WebElement login = driver.findElement(By.name("j_username"));
        login.clear();
        login.sendKeys(userName);
        
        WebElement password = driver.findElement(By.name("j_password"));
        password.clear();
        password.sendKeys(userPassword);
        
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/p/input")).click();
        
        //Create user
        driver.get(registrationUrl);
        
        WebElement newNameElement = driver.findElement(By.name("RegisterForm:name"));
        newNameElement.clear();
        newNameElement.sendKeys(newName);
        
        WebElement newSurnameElement = driver.findElement(By.name("RegisterForm:surname"));
        newSurnameElement.clear();
        newSurnameElement.sendKeys(newSurname);
        
        WebElement newEmailElement = driver.findElement(By.name("RegisterForm:email"));
        newEmailElement.clear();
        newEmailElement.sendKeys(newEmail);
        
        WebElement newLoginElement = driver.findElement(By.name("RegisterForm:login"));
        newLoginElement.clear();
        newLoginElement.sendKeys(newLogin);
        
        WebElement newPasswordElement = driver.findElement(By.name("RegisterForm:password"));
        newPasswordElement.clear();
        newPasswordElement.sendKeys(newPassword);
        
        WebElement newRepeatPasswordElement = driver.findElement(By.name("RegisterForm:passwordRepeat"));
        newRepeatPasswordElement.clear();
        newRepeatPasswordElement.sendKeys(newPassword);
        
        WebElement newQuestionElement = driver.findElement(By.name("RegisterForm:question"));
        newQuestionElement.clear();
        newQuestionElement.sendKeys(newQuestion);
        
        WebElement newAnswerElement = driver.findElement(By.name("RegisterForm:answer"));
        newAnswerElement.clear();
        newAnswerElement.sendKeys(newAnswer);
        
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();
        
        driver.get(clientListUrl);
        
        Assert.assertTrue(driver.getPageSource().contains(newLogin) &&
                driver.getPageSource().contains(newName) &&
                driver.getPageSource().contains(newSurname) &&
                driver.getPageSource().contains(newEmail));
        
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[4]/td[5]/input[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();
    }
}
