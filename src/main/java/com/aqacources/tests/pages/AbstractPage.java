package com.aqacources.tests.pages;

import com.aqacources.tests.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

/**
 * Created by Marina on 19.02.2019.
 */
public class AbstractPage {

    String currentPageURL;

    private String oldWindow;

    private String MENU_IITEM = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='%s']";

    private String MENU_SUBCATEGORY = "//li/a[@title='%s']";

    // Web Elements
    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInLink;

    @FindBy(xpath = "//div[@id='page']")
    protected WebElement divPage;

    @FindBy(xpath = "//div[@class='header_user_info']/a[@class='logout']")
    private WebElement logOut;

    @FindBy(
            xpath =
                    "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']"
    )
    private WebElement menuDresses;

    @FindBy(
            xpath =
                    "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='T-shirts']"
    )
    private WebElement menuTShirts;

    @FindBy(xpath = "//div[@class='product-container']")
    private WebElement productContainer;

    @FindBy(xpath = "//div[@class='breadcrumb clearfix']")
    private WebElement breadcrumb;

    @FindBy(xpath = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='%s']/li[@class='sfHover']")
    private WebElement menuHover;

    // Instances of BaseTest
    protected BaseTest testClass;

    /**
     * Constructor
     *
     * @param testClass
     */
    public AbstractPage(BaseTest testClass) {
        this.testClass = testClass;
        PageFactory.initElements(testClass.getDriver(), this);
        testClass.waitTillElementIsVisible(divPage);
    }

    /**
     * Click on Sign In link
     *
     * @return new instance of LoginPage
     */
    public LoginPage clickSignInLink() {
        testClass.waitTillElementIsVisible(signInLink);
        signInLink.click();
        return new LoginPage(testClass);
    }

    /**
     * Get Title
     *
     * @return
     */
    public String getTitle() {
        return testClass.getDriver().getTitle();
    }

    /**
     * Click Log Out
     */
    public void logOut() {
        testClass.waitTillElementIsVisible(logOut);
        logOut.click();
    }

    /**
     * Click to menu Dresses
     *
     * @return new instance of Category page
     */
    public DressesPage clickMenuDresses() {
        testClass.waitTillElementIsVisible(menuDresses);
        menuDresses.click();
        return new DressesPage(testClass);
    }

    /**
     * Click to menu T-shirt
     *
     * @return new instance of T-shirt page
     */
    public ProductListPage clickMenuTShirt() {
        testClass.waitTillElementIsVisible(menuTShirts);
        menuTShirts.click();
        return new ProductListPage(testClass);
    }

    /**
     * Check breadcrumb
     *
     * @param expectedBreadcrumbs
     */
    public void checkBreadrumb(String expectedBreadcrumbs) {
        testClass.waitTillElementIsVisible(breadcrumb);
        String breadcrumbs = breadcrumb.getAttribute("innerText");

        String actualBreadCrumbs = breadcrumbs.replace(" > ", "").replace(">", " ");

        //  verify breadcrumb from page with breadcrumb from enum
        Assert.assertEquals(actualBreadCrumbs, expectedBreadcrumbs);
    }

    /**
     * Move to Element Menu
     *
     * @param menuName
     */
    public void moveToElementMenu(String menuName) {
        testClass.waitTillElementIsVisible(
                testClass.getDriver().findElement(By.xpath(String.format(MENU_IITEM, menuName))));

        Actions actions = new Actions(testClass.getDriver());

        // Move to element
        actions.moveToElement(testClass.getDriver().findElement(By.xpath(String.format(MENU_IITEM, menuName)))).build().perform();

    }

    /**
     * Click to SubCategory
     *
     * @param subCategory
     * @return
     */
    public ProductListPage clickToSubCategory(String subCategory) {
        testClass.waitTillElementIsVisible(
                testClass.getDriver().findElement(By.xpath(String.format(MENU_SUBCATEGORY, subCategory))));

        testClass.getDriver().findElement(By.xpath(String.format(MENU_SUBCATEGORY, subCategory))).click();

        return new ProductListPage(testClass);
    }

    /**
     * Switch to New Window
     */
    public void switchToNewWindow() {
        String actualWindow = testClass.getDriver().getWindowHandle();

        this.oldWindow = actualWindow;

        Set <String> windows = testClass.getDriver().getWindowHandles();

        String newWindow = null;

        for (String window : windows) {
            if (!window.equals(actualWindow)) {
                newWindow = window;
            }
        }
        testClass.getDriver().switchTo().window(newWindow);

    }

    /**
     * Close Window
     */
    public void closeWindow() {
        testClass.getDriver().close();
    }

    /**
     * Switch to Parent Window
     */
    public void switchToParentWindow() {
        testClass.getDriver().switchTo().window(this.oldWindow);
    }


    /**
     * Get Cookie and print in console
     */
    public void getCookies() {

        Set <Cookie> cookies = testClass.getDriver().manage().getCookies();

        System.out.println("Cookies from page:\n" + cookies);
    }

}
