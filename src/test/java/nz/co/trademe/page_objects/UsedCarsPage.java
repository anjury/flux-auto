package nz.co.trademe.page_objects;

import nz.co.trademe.TestDriver;
import nz.co.trademe.domain.entities.Subcategory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.SECONDS;

public class UsedCarsPage {

    WebDriver driver;

    // locators

    private final By locatorCategoryPlaceholder = By.id("CategoryNavigator_CategoryPlaceholder");

    public List<Subcategory> subcategoryList = new ArrayList<Subcategory>();

    public UsedCarsPage(WebDriver driver) {
        this.driver = driver;
        Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class);
        WebElement foo = wait.until(
            d -> d.findElement(locatorCategoryPlaceholder));
        populateSubcategoryList();
    }

    public void populateSubcategoryList() {
        List<WebElement> elementList = driver.findElements(By.xpath("//*[@id='CategoryNavigator_CategoryPlaceholder']//li"));
        Pattern pattern = Pattern.compile("(\\w+)\\s\\((\\d+)\\)"); // Daihatsu (6)
        for (WebElement webElement : elementList) {
            Matcher matcher = pattern.matcher(webElement.getText());
            if (matcher.find())
            {
                System.out.println("Make: " + matcher.group(1) + " Count: " + matcher.group(2));
                subcategoryList.add(new Subcategory(matcher.group(1), Long.parseLong(matcher.group(2))));
            }
//            System.out.println("Text: " + webElement.getText());
        }
    }

}
