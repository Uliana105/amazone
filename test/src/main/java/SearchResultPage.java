import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {
    private static WebDriver driver;

    public SearchResultPage (WebDriver driver) {
        SearchResultPage.driver = driver;
    }

    private static final String category = "//ul//span[text()=\"%s\"]";
    private static final By allBooksNames = By.xpath("//div[contains(@class, \"search-results_\")]//h2//span");
    private static final String bookNamePath = "(//div[contains(@class, \"search-results_\")]//h2//span)[%d]";
    private static final String authorByLinkPath = "(//div[contains(@class, \"search-results_\")])[%d]//span[text()=\"by \"]/following-sibling::a";
    private static final String authorBySpanPath = "(//div[contains(@class, \"search-results_\")])[%d]//span[text()=\"by \"]/following-sibling::span";
    private static final String pricePath = "((//div[contains(@class, \"search-results_\")])[%d]//span[@class=\"a-price-symbol\"])[1]/following-sibling::span";
    private static final String bestsellerLabel = "(//div[contains(@class, \"search-results_\")])[%d]//span[text()=\"Best Seller\"]";

    public void setCategory(String text) {
        driver.findElement(By.xpath(String.format(category, text))).click();
    }

    public List<Book> findBooks() {
        List<Book> bookList = new ArrayList<>();
        for (int i = 1; i <= driver.findElements(allBooksNames).size(); i++) {
            bookList.add(new Book(getBookName(i).getText(), getBookAuthor(i), getBookPrice(i), isBestseller(i)));
        }
        return bookList;
    }

    private static WebElement getBookName(int bookNum) {
        return driver.findElement(By.xpath(String.format(bookNamePath, bookNum)));
    }

    private static String getBookAuthor(int bookNum) {
        StringBuilder authors = new StringBuilder();
        String path = String.format(authorByLinkPath, bookNum);
        List<WebElement> listOfAuthors = driver.findElements(By.xpath(path));
        if (listOfAuthors.size() >= 1) {
            if (listOfAuthors.size() == 1) authors.append(listOfAuthors.get(0).getText());
            else {
                for (WebElement author: listOfAuthors) {
                    authors.append(author.getText()).append(", ");
                }
            }
        } else {
            path = String.format(authorBySpanPath, bookNum);
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
        String path = String.format(pricePath, bookNum);
        List<WebElement> listOfPrice = driver.findElements(By.xpath(path));
        if (listOfPrice.size() == 2) price.append(listOfPrice.get(0).getText()).append(".").append(listOfPrice.get(1).getText());
        else price.append("0");
        return price.toString();
    }

    private static boolean isBestseller(int bookNum) {
        String path = String.format(bestsellerLabel, bookNum);
        System.out.println(bookNum);
        return driver.findElements(By.xpath(path)).size() != 0;
    }
}
