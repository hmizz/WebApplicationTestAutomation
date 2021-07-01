import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert;

import static junit.framework.TestCase.assertEquals;

public class BasicTest extends TestHelper {


    private String username = "";
    private String password = "";

    @Test
    public void titleExistsTest(){
        String expectedTitle = "MedTech Online Store";
        String actualTitle = driver.getTitle();

        assertEquals(expectedTitle, actualTitle);
    }


    /*
    
    Fill in loginLogoutTest() and login mehtod in TestHelper, so that the test passes correctly.

     */
     @Test
    public void loginLogoutTest(){

        
        	login(username, password);

            // assert that correct page appeared
            // WebElement adminHeader = driver.findElement...
            // ...
            boolean isPresent = isElementPresent(By.linkText("New product"));
            assertTrue(isPresent);

            logout();
        }

    /*

     Write a test case, where you make sure, that one can’t log in with a false password

     */
     
	@Test
    public void loginFalsePassword() {
    	 login("admin", "1234");
         Assert.assertEquals("Invalid user/password combination", driver.findElement(By.id("notice")).getText());

    }

}
