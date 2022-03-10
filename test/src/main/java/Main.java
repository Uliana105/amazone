import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.get("https://www.amazon.com/");
        driver.get("https://www.amazon.com/s?k=Java&i=stripbooks-intl-ship&crid=19DTRY566O0P4&sprefix=java%2Cstripbooks-intl-ship%2C691&ref=nb_sb_noss_1");
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[text()=\" UA\"]")));
        driver.findElement(By.xpath("(//div[@class=\"glow-toaster-content\"]//span[@class=\"a-button-inner\"])[1]")).click();
        driver.findElement(By.xpath("//input[@id=\"twotabsearchtextbox\"]")).sendKeys("Java");
        driver.findElement(By.xpath("//div[@id=\"nav-search-dropdown-card\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()=\"Books\"]")));
        driver.findElement(By.xpath("//option[text()=\"Books\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"Go\"]")).click();*/


        List<Book> bookList = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            bookList.add(new Book(getBookName(i).getText(), getBookAuthor(i), getBookPrice(i), isBestseller(i)));
        }

        for (Book book: bookList) {
            if (book.getName().equals("Head First Java, 2nd Edition")) {
                System.out.println("Head First Java, 2nd Edition is present");
                break;
            }
        }
    }

    private static WebElement getBookName(int bookNum) {
        return driver.findElement(By.xpath(String.format("(//div[contains(@class, \"search-results_\")]//h2//span)[%d]", bookNum)));
    }

    private static String getBookAuthor(int bookNum) {
        StringBuilder authors = new StringBuilder();
        String path = String.format("(//div[contains(@class, \"search-results_\")])[%d]//span[text()=\"by \"]/following-sibling::a", bookNum);
        List<WebElement> listOfAuthors = driver.findElements(By.xpath(path));
        if (listOfAuthors.size() >= 1) {
            if (listOfAuthors.size() == 1) authors.append(listOfAuthors.get(0).getText());
            else {
                for (WebElement author: listOfAuthors) {
                    authors.append(author.getText()).append(", ");
                }
            }
        } else {
            path = String.format("(//div[contains(@class, \"search-results_\")])[%d]//span[text()=\"by \"]/following-sibling::span", bookNum);
            listOfAuthors = driver.findElements(By.xpath(path));
            for (WebElement text : listOfAuthors) {
                if (text.getText().equals("")) break;
                authors.append(text.getText()).append(" ");
            }
        }
        return authors.toString();
    }

    private static String getBookPrice(int bookNum) {
        StringBuilder price = new StringBuilder();
        String path = String.format("((//div[contains(@class, \"search-results_\")])[%d]//span[@class=\"a-price-symbol\"])[1]/following-sibling::span", bookNum);
        List<WebElement> listOfPrice = driver.findElements(By.xpath(path));
        if (listOfPrice.size() == 2) price.append(listOfPrice.get(0).getText()).append(".").append(listOfPrice.get(1).getText());
        else price.append("0");
        return price.toString();
    }

    private static boolean isBestseller(int bookNum) {
        String path = String.format("(//div[contains(@class, \"search-results_\")])[%d]//span[text()=\"Best Seller\"]", bookNum);
        return driver.findElements(By.xpath(path)).size() > 0;
    }
}
