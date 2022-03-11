import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainClassTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.amazon.com/");
        MainPage mainPage = new MainPage(driver);
        mainPage.search("Java");
    }

    @Test
    public void test() {
        SearchResultPage resultPage = new SearchResultPage(driver);
        resultPage.setCategory("Books");
        List<Book> bookList = resultPage.findBooks();
        List<String> names = new ArrayList<>();
        for (Book book:bookList) {
            names.add(book.getName());
        }

        Assert.assertTrue(names.contains("Head First Java, 2nd Edition"));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
