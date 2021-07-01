import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestHelper {

    static WebDriver driver;
    final int waitForResposeTime = 4;
	
	// here write a link to your admin website 
    String baseUrlAdmin = "https://sleepy-bayou-36738.herokuapp.com/admin";
	
	// here write a link to your website 
    String baseUrl = "https://sleepy-bayou-36738.herokuapp.com";

    @Before
    public void setUp(){

        // if you use Chrome:
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hamez\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();

        // if you use Firefox:
        //System.setProperty("webdriver.gecko.driver", "C:\\Users\\...\\geckodriver.exe");
        //driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);

    }

    void goToPage(String page){
        WebElement elem = driver.findElement(By.linkText(page));
        elem.click();
        waitForElementById(page);
    }

    void waitForElementById(String id){
        new WebDriverWait(driver, waitForResposeTime).until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    void login(String username, String password){

        driver.get(baseUrlAdmin);

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("name")).sendKeys("admin");

        driver.findElement(By.id("password")).sendKeys("admin");

        By loginButtonXpath = By.xpath("/html/body/div[4]/div[2]/div[2]/form/div[3]/input");
        // click on the button
        driver.findElement(loginButtonXpath).click();
    }

    void logout(){
        WebElement logout = driver.findElement(By.linkText("Logout"));
        logout.click();

        waitForElementById("Admin");
    }
    void register(){
        driver.get(baseUrlAdmin);
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("Name")).sendKeys("admin2");
        driver.findElement(By.id("Password")).sendKeys("admin2");
        driver.findElement(By.id("Confirm")).sendKeys("admin2");
        driver.findElement(By.name("Create User")).click();
    }

    @After
    public void tearDown(){
        driver.close();
    }

}