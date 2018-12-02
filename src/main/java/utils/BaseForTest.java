package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseForTest {

    public static void waitElementClickable(WebDriver driver, By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Метод для получения вэб драйвера
     *
     * @return драйвер для хрома
     */
    public static WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "C:\\Java\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    /**
     * Метод для открытия страницы
     *
     * @param driver веб драйвер
     * @param url    адрес страницы
     */
    public static void openPage(WebDriver driver, String url) {
        By by = By.xpath("//*[@id=\"header-top\"]/div/div[1]/ul/li[1]/div/a[1]");
        driver.get(url);
        try {
//            waitElementClickable(driver, by, 10);
            driver.findElement(by).click();
        } catch (NoSuchElementException ex) {
        }
    }

    /**
     * Метод для перехода между страницами
     *
     * @param driver веб драйвер
     * @param url    адрес страницы
     */
    public static void goToPage(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void closeBrowser(WebDriver driver) {
        driver.quit();
    }

    /**
     * Метод для наведения мыши на категорию
     *
     * @param driver   веб драйвер
     * @param category имя категории
     */
    public static void selectMainCategory(WebDriver driver, MainCategory category) {
        By by = By.linkText(category.getCategory());
        waitElementClickable(driver, by, 10);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    /**
     * Метод для клика по субкатегории
     *
     * @param driver   веб драйвер
     * @param category имя категории
     */
    public static void selectSubCategory(WebDriver driver, SubCategory category) {
        By by = By.linkText(category.getCategory());
        waitElementClickable(driver, by, 10);
        driver.findElement(by).click();
    }

    /**
     * Метод для изменения сортировки по цене
     *
     * @param driver веб драйвер
     * @param sort   требуемая сортировка
     */
    public static void priceSort(WebDriver driver, PriceSort sort) {
        int elem = 0;

        By priceSortList = By.xpath("//*[@id=\"sort-filter\"]/div[1]/div/button/span[2]");
        waitElementClickable(driver, priceSortList, 10);
        if (!driver.findElement(priceSortList).getText().contentEquals(sort.getCriteria())) {
            if (sort == PriceSort.LOW_TO_MAX)
                elem = 1;
            if (sort == PriceSort.MAX_TO_LOW)
                elem = 2;
            System.out.println("Выбирается новый критерий сортировки по цене: " + sort.getCriteria());
            driver.findElement(priceSortList).click();
            By checkCriteria = By.xpath("//*[@id=\"sort-filter\"]/div[1]/div/ul/li[" + elem + "]/a");
            waitElementClickable(driver, checkCriteria, 10);
            driver.findElement(checkCriteria).click();
            waitElementClickable(driver, By.xpath("//*[@id=\"catalog-items-page\"]/div[6]/div[5]/div[1]/div[2]/a"), 10);
            //*[@id="sort-filter"]/div[1]/div/ul/li[1]/a
            //*[@id="sort-filter"]/div[1]/div/ul/li[2]/a
        } else {
            System.out.println("Данный критерий сортировки цены уже выбран: " + sort.getCriteria());
        }
    }

    /**
     * Метод для выбора товара
     *
     * @param driver
     * @param number порядковый номер товара
     * @return объект класса товаров
     */
    public static Item getItem(WebDriver driver, int number) {
        //System.out.println("count: " + number);
        Item item = new Item();
        By name = By.xpath("//*[@id=\"catalog-items-page\"]/div[6]/div[5]/div[1]/div[1]/div[" + number + "]/div/div[1]/div[2]/a/h3");
        By price = By.xpath("//*[@id=\"catalog-items-page\"]/div[6]/div[5]/div[1]/div[1]/div[" + number + "]/div/div[2]/div/div[1]");
        waitElementClickable(driver, name, 10);
        if (driver.findElement(price).getText().contains("%")) {
            price = By.xpath("//*[@id=\"catalog-items-page\"]/div[6]/div[5]/div[1]/div[1]/div[" + number + "]/div/div[2]/div/div[2]");
        }
        item.setName(driver.findElement(name).getText());
        item.setPrice(driver.findElement(price).getText());
        item.show();
        return item;
    }

    /**
     * Подсчитывает количество товаров на странице
     *
     * @param driver
     * @return
     */
    public static Integer countItem(WebDriver driver) {
        By by = By.className("product-info");
        waitElementClickable(driver, by, 10);
        return driver.findElements(by).size();
    }

    public static void lastPage(WebDriver driver) {
        By last = By.xpath("//div[@class='pagination']/span[contains(text(), 'В конец')]");
        waitElementClickable(driver, last, 10);
        driver.findElement(last).click();
    }

    public static void compareToItems(Item item1, Item item2) {
        if (item1.getPrice().contentEquals(item2.getPrice()))
            System.out.println("Цены у товаров равны");
        else
            System.err.println("Цены у товаров не равны");

        if (item1.getName().contentEquals(item2.getName()))
            System.out.println("Названия у товаров одинаковые");
        else
            System.err.println("Названия у товаров разные");
    }
}
