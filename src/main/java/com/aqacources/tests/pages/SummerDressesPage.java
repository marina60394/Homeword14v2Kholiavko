package com.aqacources.tests.pages;

import com.aqacources.tests.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Marina on 28.02.2019.
 */
public class SummerDressesPage extends AbstractPage {

    // Web Elements
    @FindBy(xpath = "//input[@style='background: #ffffff;']")
    private WebElement filterColorWhite;

    @FindBy(xpath = "//div[@class='product-container']")
    private List <WebElement> productContainer;

    @FindBy(xpath = "//h1[@class='page-heading product-listing']/span[@class='heading-counter']")
    private WebElement productCounter;

    /**
     * Constructor
     *
     * @param testClass
     */
    public SummerDressesPage(BaseTest testClass) {
        super(testClass);
    }

    /**
     * click to filter color White
     */
    public void clickFilterColorWhite() {
        testClass.waitTillElementIsVisible(filterColorWhite);
        filterColorWhite.click();

        testClass.waitTillLoaderIsNotPresent();
    }

    /**
     * verify that product amount from the message is the same with the amount of products on the page
     */
    public void compareProductAmounts() {

        testClass.waitTillElementIsVisible(productCounter);

        // get product amount from message on the page
        String productCounterAmount = productCounter.getText().replaceAll("[^\\d]", "");

        int productCounterValue = Integer.parseInt(productCounterAmount);

        Assert.assertEquals(productContainer.size(), productCounterValue);
    }
}
