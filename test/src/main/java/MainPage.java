import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class MainPage {
    private static WebDriver driver;

    public MainPage (WebDriver driver) {
        MainPage.driver = driver;
    }

    @FindBy(xpath = "//input[@id=\"twotabsearchtextbox\"]")
    private WebElement inputField;

    @FindBy(xpath = "//input[@value=\"Go\"]")
    private WebElement searchButton;

    public void search(String text) {
        setTextInInputField(text);
        runSearch();
    }

    private void setTextInInputField(String text) {
        inputField.sendKeys(text);
    }

    private void runSearch() {
        searchButton.click();
    }
}
