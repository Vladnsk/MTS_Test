import org.openqa.selenium.WebDriver;
import utils.Item;

import static utils.BaseForTest.*;
import static utils.MainCategory.NOTEBOOKS_AND_TABLETS;
import static utils.PriceSort.LOW_TO_MAX;
import static utils.PriceSort.MAX_TO_LOW;
import static utils.SubCategory.EBOOK;
import static utils.SubCategory.NOTEBOOKS;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = getWebDriver();

//      Notebooks
        openPage(driver, "https://www.dns-shop.ru");
        selectMainCategory(driver, NOTEBOOKS_AND_TABLETS);
        selectSubCategory(driver, NOTEBOOKS);
        priceSort(driver, LOW_TO_MAX);
        Item item = getItem(driver, 1);
        priceSort(driver, MAX_TO_LOW);
        lastPage(driver);
        //TODO: без засыпания почему-то количество товаров на странице подсчитывается до перехода на последнюю стр
        Thread.sleep(5000);
        Item newItem = getItem(driver, countItem(driver));
        compareToItems(item, newItem);

//        Other
        openPage(driver, "https://www.dns-shop.ru");
        selectMainCategory(driver, NOTEBOOKS_AND_TABLETS);
        selectSubCategory(driver, EBOOK);
        priceSort(driver, LOW_TO_MAX);
        item = getItem(driver, 1);
        priceSort(driver, MAX_TO_LOW);
        lastPage(driver);
        Thread.sleep(5000);
        newItem = getItem(driver, countItem(driver));
        compareToItems(item, newItem);
        closeBrowser(driver);
    }
}
