import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class SeleniumWithJUnitLiveTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        SeleniumExample seleniumExample = new SeleniumExample();
        driver = seleniumExample.getDriver();
    }

    @Test
    public void testCaseOne() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addElement = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
        addElement.click();

        WebElement delete = driver.findElement(By.xpath("//button[contains(text(),'Delete')]"));
        delete.click();
        try {
            delete.isDisplayed();
        }
        catch (org.openqa.selenium.StaleElementReferenceException e){
            System.out.println("The Delete button is not present");;
            }
    }

    @Test
    public void testCaseTwo() {
        driver.get("http://the-internet.herokuapp.com/broken_images");


        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("Total number of Images on the Page are " + images.size());


        //checking the links fetched.
        for(int index=0;index<images.size();index++)
        {
            WebElement image= images.get(index);
            String imageURL= image.getAttribute("src");
            System.out.println("URL of Image " + (index+1) + " is: " + imageURL);
            SeleniumExample.verifyLinks(imageURL);

            //Validate image display using JavaScript executor
            try {
                String typeof =
                        "return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);";
                boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript(typeof, image);
                if (imageDisplayed) {
                    System.out.println("DISPLAY - OK");
                }else {
                    System.out.println("DISPLAY - BROKEN");
                }
            }
            catch (Exception e) {
                System.out.println("Error Occurred");
            }
        }
    }

    @Test
    public void testCaseThree() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByValue("2");

        boolean isOptionTwoSelected = driver.findElement(By.xpath(
                "//option[contains(text(),'Option 2')]")).isSelected();
        if (isOptionTwoSelected){
            System.out.println("The Option 2 is selected");
        }
        else {
            System.out.println("!!!!The Option 2 is NOT selected");
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
