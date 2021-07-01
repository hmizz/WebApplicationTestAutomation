
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

public class AdminTest extends TestHelper  {

    @Before
    public void before() {
        register();
    }

   

    @Test
    public void deleteAccount() {
        //The before and after parts do all the testing necessary
    }

    @Test
    public void addProduct() {
        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_title")).sendKeys("testItem");
        driver.findElement(By.id("product_description")).sendKeys("testItem desc");
        Select type = new Select(driver.findElement(By.id("product_prod_type")));
        type.selectByValue("Books");
        driver.findElement(By.id("product_price")).sendKeys("20");

        driver.findElement(By.name("commit")).click();

        try {
            assertTrue(driver.findElement(By.id("testItem")).findElements(By.linkText("testItem")).size() > 0);
        } finally {
            driver.findElement(By.id("testItem")).findElement(By.linkText("Delete")).click();
            assertFalse(driver.findElements(By.id("testItem")).size() > 0);
        }

    }

    @Test
    public void editProductType() {
        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_title")).sendKeys("testItem");
        driver.findElement(By.id("product_description")).sendKeys("testItem desc");
        Select type = new Select(driver.findElement(By.id("product_prod_type")));
        type.selectByValue("Books");
        driver.findElement(By.id("product_price")).sendKeys("20");
        driver.findElement(By.name("commit")).click();

        driver.findElement(By.id("testItem")).findElement(By.linkText("Edit")).click();

        Select type2 = new Select(driver.findElement(By.id("product_prod_type")));
        type2.selectByValue("Sunglasses");

        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Back")).click();

        assertTrue(driver.findElement(By.id("testItem")).findElements(By.linkText("testItem")).size() > 0);
        driver.findElement(By.id("testItem")).findElement(By.linkText("testItem")).click();

        try {
            assertEquals("Type: Sunglasses",driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/p[4]")).getText());
        } finally {
            driver.findElement(By.linkText("Back")).click();
            driver.findElement(By.id("testItem")).findElement(By.linkText("Delete")).click();
        }

    }

    @Test
    public void editProductTitle() {
        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_title")).sendKeys("testItem");
        driver.findElement(By.id("product_description")).sendKeys("testItem desc");
        Select type = new Select(driver.findElement(By.id("product_prod_type")));
        type.selectByValue("Books");
        driver.findElement(By.id("product_price")).sendKeys("20");
        driver.findElement(By.name("commit")).click();

        driver.findElement(By.id("testItem")).findElement(By.linkText("Edit")).click();

        driver.findElement(By.id("product_title")).clear();
        driver.findElement(By.id("product_title")).sendKeys("testItemEdited");

        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Back")).click();

        assertTrue(driver.findElement(By.id("testItemEdited")).findElements(By.linkText("testItemEdited")).size() > 0);
        driver.findElement(By.id("testItemEdited")).findElement(By.linkText("testItemEdited")).click();

        try {
            assertEquals("Title: testItemEdited",driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/p[2]")).getText());
        } finally {
            driver.findElement(By.linkText("Back")).click();
            driver.findElement(By.id("testItemEdited")).findElement(By.linkText("Delete")).click();
        }

    }

    @Test
    public void editProductDesc() {
        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_title")).sendKeys("testItem");
        driver.findElement(By.id("product_description")).sendKeys("testItem desc");
        Select type = new Select(driver.findElement(By.id("product_prod_type")));
        type.selectByValue("Books");
        driver.findElement(By.id("product_price")).sendKeys("20");
        driver.findElement(By.name("commit")).click();

        driver.findElement(By.id("testItem")).findElement(By.linkText("Edit")).click();

        driver.findElement(By.id("product_description")).clear();
        driver.findElement(By.id("product_description")).sendKeys("testItem desc Edited");


        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Back")).click();

        assertTrue(driver.findElement(By.id("testItem")).findElements(By.linkText("testItem")).size() > 0);
        driver.findElement(By.id("testItem")).findElement(By.linkText("testItem")).click();

        try {
            assertEquals("Description: testItem desc Edited",driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/p[3]")).getText());
        } finally {
            driver.findElement(By.linkText("Back")).click();
            driver.findElement(By.id("testItem")).findElement(By.linkText("Delete")).click();
        }

    }

    @Test
    public void editProductPrice() {
        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_title")).sendKeys("testItem");
        driver.findElement(By.id("product_description")).sendKeys("testItem desc");
        Select type = new Select(driver.findElement(By.id("product_prod_type")));
        type.selectByValue("Books");
        driver.findElement(By.id("product_price")).sendKeys("20");
        driver.findElement(By.name("commit")).click();

        driver.findElement(By.id("testItem")).findElement(By.linkText("Edit")).click();

        driver.findElement(By.id("product_price")).clear();
        driver.findElement(By.id("product_price")).sendKeys("22");


        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Back")).click();

        assertTrue(driver.findElement(By.id("testItem")).findElements(By.linkText("testItem")).size() > 0);
        driver.findElement(By.id("testItem")).findElement(By.linkText("testItem")).click();

        try {
            assertEquals("Price: €22.00",driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/p[5]")).getText());
        } finally {
            driver.findElement(By.linkText("Back")).click();
            driver.findElement(By.id("testItem")).findElement(By.linkText("Delete")).click();
        }

    }

    @Test
    public void deleteProduct() {
        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_title")).sendKeys("testItem");
        driver.findElement(By.id("product_description")).sendKeys("testItem desc");
        Select type = new Select(driver.findElement(By.id("product_prod_type")));
        type.selectByValue("Books");
        driver.findElement(By.id("product_price")).sendKeys("20");
        driver.findElement(By.name("commit")).click();

        driver.findElement(By.id("testItem")).findElement(By.linkText("Delete")).click();

        assertFalse(driver.findElements(By.id("testItem")).size() > 0);

    }

    @Test
    public void canNotRegisterWithNameThatExists() {
        driver.get(baseUrlAdmin);
        driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[3]/a")).click();
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("user_name")).sendKeys("admin2");
        driver.findElement(By.id("user_password")).sendKeys("admin2");
        driver.findElement(By.id("user_password_confirmation")).sendKeys("admin2");
        driver.findElement(By.name("commit")).click();
        try {
            assertEquals("Name has already been taken", driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/form/div[1]/ul/li")).getText());
        } finally {
            driver.get("https://powerful-taiga-62172.herokuapp.com/login");
            driver.findElement(By.id("name")).sendKeys("admin2");
            driver.findElement(By.id("password")).sendKeys("admin2");
            driver.findElement(By.xpath("//input[@value='Login']")).click();
        }
    }

    @Test
    public void canNotAddProductWithTitleThatExists() {
        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_title")).sendKeys("testItem");
        driver.findElement(By.id("product_description")).sendKeys("testItem desc");
        Select type = new Select(driver.findElement(By.id("product_prod_type")));
        type.selectByValue("Books");
        driver.findElement(By.id("product_price")).sendKeys("20");

        driver.findElement(By.name("commit")).click();

        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_title")).sendKeys("testItem");
        driver.findElement(By.id("product_description")).sendKeys("testItem desc2");
        Select type2 = new Select(driver.findElement(By.id("product_prod_type")));
        type2.selectByValue("Sunglasses");
        driver.findElement(By.id("product_price")).sendKeys("24");

        driver.findElement(By.name("commit")).click();

        try {
            assertEquals("Title has already been taken", driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/form/div[1]/ul/li")).getText());
        } finally {
            driver.get("https://powerful-taiga-62172.herokuapp.com/products");
            driver.findElement(By.id("testItem")).findElement(By.linkText("Delete")).click();
        }
    }

    @Test
    public void canNotAddProductWithoutTitle() {
        driver.findElement(By.linkText("New product")).click();

        driver.findElement(By.id("product_description")).sendKeys("testItem desc");
        Select type = new Select(driver.findElement(By.id("product_prod_type")));
        type.selectByValue("Books");
        driver.findElement(By.id("product_price")).sendKeys("20");

        driver.findElement(By.name("commit")).click();

        assertEquals("Title can't be blank", driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/form/div[1]/ul/li")).getText());

    }


    @After
    public void after() {
        driver.get(baseUrlAdmin);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.linkText("Delete")).click();
        String deleteString = driver.findElement(By.id("notice")).getText();
        assertEquals("User was successfully deleted.", deleteString);
    }



}
