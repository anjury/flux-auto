package nz.co.trademe.pages;

import nz.co.trademe.entities.Subcategory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsedCarsPage {

    final WebDriver driver;

    private final By locatorCategoryPlaceholder = By.id("CategoryNavigator_CategoryPlaceholder");
    private final By locatorSubcategoryList = By.xpath("//*[@id='CategoryNavigator_CategoryPlaceholder']//li");

    public List<Subcategory> subcategoryList = new ArrayList<>();

    public UsedCarsPage(WebDriver driver) {
        this.driver = driver;
        Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class);
        WebElement waitElement = wait.until(
            d -> d.findElement(locatorCategoryPlaceholder));
        populateSubcategoryList();
    }

    public void populateSubcategoryList() {
        List<WebElement> elementList = driver.findElements(locatorSubcategoryList);
        Pattern pattern = Pattern.compile("(\\w+)\\s\\((\\d+)\\)"); // Daihatsu (6)
        for (WebElement webElement : elementList) {
            Matcher matcher = pattern.matcher(webElement.getText());
            if (matcher.find())
            {
                subcategoryList.add(new Subcategory(matcher.group(1), Long.parseLong(matcher.group(2))));
            }
        }
    }

}
