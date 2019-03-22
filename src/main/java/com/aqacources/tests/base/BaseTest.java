package com.aqacources.tests.base;

import com.aqacources.tests.pages.HomePage;
import com.aqacources.tests.utils.YamlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Marina on 24.02.2019.
 */
public class BaseTest {

    // Instance of WebDriver
    private WebDriver driver;
    private WebDriverWait wait;

    // Logger
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    // Rule
    @Rule
    public RunTestRule runTestRule = new RunTestRule(this);

    // Product loader element
    private static final String LOADING_PRODUCT = "//ul[@class='product_list grid row']/p";

    /**
     * Constructor
     */
    public BaseTest() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * Return instance of Driver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Open site and return instance of HomePage
     *
     * @return HomePage
     */
    public HomePage openSite() {
        driver.get(YamlParser.getYamlData().getUrl());
        return new HomePage(this);
    }

    /**
     * Close site with driver.quit()
     */
    public void closeSite() {
        driver.quit();
    }

    /**
     * Wait till element is visible
     *
     * @param element
     */
    public void waitTillElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait till Loader isn't present
     */
    public void waitTillLoaderIsNotPresent() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOADING_PRODUCT)));
    }

    /**
     * Wait till text to be present in element
     */
    public void waitTillTextToBePresentInElementValue(String locator, int expectedValue) {
        wait.until(
                ExpectedConditions.textToBePresentInElementValue(
                        By.xpath(locator), String.valueOf(expectedValue)));
    }

    /**
     * Wait till Element is Present
     *
     * @param locator
     */
    public void waitTillElementIsPresent(String locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    /**
     * Write down info message
     *
     * @param message
     */
    public void log(String message) {
        logger.info(message);
    }

    /**
     * Write down error message
     *
     * @param error
     */
    public void error(String error) {
        logger.error(error);
    }

    /**
     * Get current date and time
     *
     * @return current date and time
     */
    public String getDateTime() {
        return new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
    }
}
