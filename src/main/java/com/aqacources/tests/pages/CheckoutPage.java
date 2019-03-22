package com.aqacources.tests.pages;

import com.aqacources.tests.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Marina on 27.02.2019.
 */
public class CheckoutPage extends AbstractPage {

    private static final String PRODUCT_QUANTITY =
            "//td[@class='cart_quantity text-center']/input[@type='hidden']";
    private static final String MESSAGE_EMPTY_SHOPPING_CART = "Your shopping cart is empty.";
    private static final double DELTA = 1e-15;

    //   Web Elements
    @FindBy(xpath = "//i[@class='icon-plus']")
    private WebElement iconIncreaseQuantity;

    @FindBy(xpath = "//td[@class='cart_total']/span[@class='price']")
    private WebElement totalPrice;

    @FindBy(xpath = "//td[@class='cart_quantity text-center']/input[@type='hidden']")
    private WebElement productQuantity;

    @FindBy(xpath = "//td[@class='cart_unit']/span[@class='price']/span[@class='price']")
    private WebElement productPrice;

    @FindBy(
            xpath = "//td[@class='cart_delete text-center']/div/a[@title='Delete']/i[@class='icon-trash']"
    )
    private WebElement deleteProduct;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    private WebElement messageEmptyCart;

    /**
     * Constructor
     *
     * @param testClass
     */
    public CheckoutPage(BaseTest testClass) {
        super(testClass);
    }

    /**
     * Wait for increase quantity of products
     */
    public void waitIncreaseElementValue() {
        testClass.waitTillElementIsPresent(PRODUCT_QUANTITY);
        String currentQuantity =
                testClass.getDriver().findElement(By.xpath(PRODUCT_QUANTITY)).getAttribute("value");

        int currentQuantityInt = Integer.parseInt(currentQuantity);

        int expectedQuantity = currentQuantityInt + 1;

        testClass.waitTillTextToBePresentInElementValue(PRODUCT_QUANTITY, expectedQuantity);
    }

    /**
     * increase quantity of products
     */
    public void clickToIncreaseQuantity() {
        testClass.waitTillElementIsVisible(iconIncreaseQuantity);

        iconIncreaseQuantity.click();

        waitIncreaseElementValue();
    }

    /**
     * check total price is the same productPrice*quantity
     */
    public void checkTotalPrice() {
        testClass.waitTillElementIsVisible(productPrice);
        testClass.waitTillElementIsVisible(totalPrice);

        // get product price
        String productPriceText = productPrice.getText().substring(1);

        // get total Price
        String totalPriceText = totalPrice.getText().substring(1);

        // get quantity of products
        String productQuantityValue = productQuantity.getAttribute("value").toString();

        float prQuantity = Integer.parseInt(productQuantityValue);

        float productPrice = Float.parseFloat(productPriceText);

        float amountTotalPrice = Float.parseFloat(totalPriceText);

        // count total amount
        float checkTotalPrice = productPrice * prQuantity;

        // check total amount and productPrice*quantity
        Assert.assertEquals(amountTotalPrice, checkTotalPrice, DELTA);
    }

    /**
     * Click Delete Product
     */
    public void clickDeleteProduct() {
        testClass.waitTillElementIsVisible(deleteProduct);
        deleteProduct.click();
    }

    /**
     * Check Empty Cart
     */
    public void checkEmptyCart() {
        testClass.waitTillElementIsVisible(messageEmptyCart);

        // check message for empty cart
        Assert.assertEquals(MESSAGE_EMPTY_SHOPPING_CART, messageEmptyCart.getText());
    }
}
