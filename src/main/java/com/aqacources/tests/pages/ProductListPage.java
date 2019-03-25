package com.aqacources.tests.pages;

import com.aqacources.tests.base.BaseTest;
import org.openqa.selenium.By;

/**
 * Created by Marina on 26.02.2019.
 */
public class ProductListPage extends AbstractPage {

    /**
     * Constructor
     *
     * @param testClass
     */
    public ProductListPage(BaseTest testClass) {
        super(testClass);
    }

    private String PRODUCT_DETAILS =
            "//div[@class='product-container']/div[@class='right-block']/h5[@itemprop='name']/a[@title='%s']";

    /**
     * Click to Product Page
     *
     * @return new instance of Product page
     */
    public ProductPage clickToProduct(String productName) {
        testClass.waitTillElementIsVisible(
                testClass.getDriver().findElement(By.xpath(String.format(PRODUCT_DETAILS, productName))));
        testClass
                .getDriver()
                .findElement(By.xpath(String.format(PRODUCT_DETAILS, productName)))
                .click();
        return new ProductPage(testClass);
    }

}
