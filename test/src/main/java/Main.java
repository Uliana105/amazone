import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.amazon.com/");

        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.search("Java");
        SearchResultPage resultPage = new SearchResultPage(driver);
        resultPage.setCategory("Books");
        List<Book> bookList = resultPage.findBooks();

        for (Book book: bookList) {
            if (book.getName().equals("Head First Java, 2nd Edition")) {
                System.out.println("Head First Java, 2nd Edition is present");
                break;
            }
        }

        driver.quit();
    }
}
