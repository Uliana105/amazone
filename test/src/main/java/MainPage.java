import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MainPage {
    private static WebDriver driver;

    public MainPage (WebDriver driver) {
        MainPage.driver = driver;
    }

    private final By inputField = By.xpath("//input[@id=\"twotabsearchtextbox\"]");
    private final By declineChangeLocationButton = By.xpath("(//div[@class=\"glow-toaster-content\"]//span[@class=\"a-button-inner\"])[1]");
    private final By searchButton = By.xpath("//input[@value=\"Go\"]");

    public void search(String text) {
        driver.findElement(inputField).sendKeys(text);
        driver.findElement(searchButton).click();
    }

    private void setTextInInputField(String text) {
        driver.findElement(inputField).sendKeys(text);
    }

    private void declineChangeLocation() {
        driver.findElement(declineChangeLocationButton).click();
    }

    private void runSearch() {
        driver.findElement(searchButton).click();
    }
}
