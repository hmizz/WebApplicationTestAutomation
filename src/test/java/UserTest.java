
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static junit.framework.TestCase.*;

public class UserTest extends TestHelper {

    @BeforeClass
    public static void createTestProduct() {

    }

    @Test
    public void addToCart() {
        driver.findElements(By.xpath("//input[@value='Add to Cart']")).get(0).click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_row"));
        assertEquals(1, cartItems.size());
    }

    @Test
    public void increaseAndDecrease() {

        driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();

        WebElement cartItem = driver.findElement(By.className("cart_row"));
        WebElement amount = cartItem.findElement(By.cssSelector("td"));

        assertEquals("1×", amount.getText());

        List<WebElement> buttons = cartItem.findElements(By.cssSelector("a"));
        buttons.get(1).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cartItem = driver.findElement(By.className("cart_row"));
        amount = cartItem.findElement(By.cssSelector("td"));

        assertEquals("2×", amount.getText());

        buttons = cartItem.findElements(By.cssSelector("a"));
        buttons.get(0).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cartItem = driver.findElement(By.className("cart_row"));
        amount = cartItem.findElement(By.cssSelector("td"));
        assertEquals("1×", amount.getText());
    }

    @Test
    public void deleteOneByOne() {

        driver.findElements(By.xpath("//input[@value='Add to Cart']")).get(0).click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_row"));
        assertEquals(1, cartItems.size());
        cartItems.get(0).findElements(By.cssSelector("a")).get(2).click();


        cartItems = driver.findElements(By.className("cart_row"));
        assertTrue(cartItems.isEmpty());
    }

    @Test
    public void deleteEntireCart() {

        driver.findElements(By.xpath("//input[@value='Add to Cart']")).get(0).click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_row"));
        assertEquals(1, cartItems.size());

        driver.findElement(By.xpath("//input[@value='Empty cart']")).click();

        cartItems = driver.findElements(By.className("cart_row"));
        assertTrue(cartItems.isEmpty());
    }

    @Test
    public void searchViaKeyword() {

        driver.findElement(By.id("search_input")).sendKeys("B45593 Sunglasses");

        List<WebElement> products = driver.findElements(By.className("entry"));
        assertFalse(products.isEmpty());
        assertEquals("B45593 Sunglasses", products.get(0).findElement(By.cssSelector("h3")).getText());
    }

    @Test
    public void searchViaWrongKeyword() {

        driver.findElement(By.id("search_input")).sendKeys("WRONG INTPUT");

        List<WebElement> products = driver.findElements(By.className("entry"));
        products.forEach(p -> assertEquals("none", p.getCssValue("display")));
    }

    @Test
    public void filterItems() {
        driver.findElement(By.linkText("Sunglasses")).click();
        List<WebElement> products = driver.findElements(By.className("entry"));
        products.forEach(v -> assertTrue(v.findElement(By.id("category"))
                .findElement(By.cssSelector("strong"))
                .getText()
                .contains("Sunglasses")));

        driver.findElement(By.linkText("Books")).click();
        products = driver.findElements(By.className("entry"));
        products.forEach(v -> assertTrue(v.findElement(By.id("category"))
                .findElement(By.cssSelector("strong"))
                .getText()
                .contains("Books")));

        driver.findElement(By.linkText("Other")).click();
        products = driver.findElements(By.className("entry"));
        products.forEach(v -> {
            String category = v.findElement(By.id("category")).findElement(By.cssSelector("strong")).getText();
            assertTrue(!category.contains("Sunglasses") && !category.contains("Books"));
        });
    }

    @Test
    public void purchaseItems() {
        driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
        driver.findElement(By.xpath("//input[@value='Checkout']")).click();

        driver.findElement(By.id("order_name")).sendKeys("test user");
        driver.findElement(By.id("order_address")).sendKeys("test address");
        driver.findElement(By.id("order_email")).sendKeys("test email");
        new Select(driver.findElement(By.id("order_pay_type"))).selectByIndex(1);

        driver.findElement(By.xpath("//input[@value='Place Order']")).click();

        String confirmation = driver.findElement(By.id("order_receipt")).findElement(By.cssSelector("h3")).getText();
        assertEquals("Thank you for your order", confirmation);
    }

    @After
    public void cleanUp() {
        List<WebElement> webElements = driver.findElements(By.xpath("//input[@value='Empty cart']"));
        if (!webElements.isEmpty()) webElements.get(0).click();
    }
}
