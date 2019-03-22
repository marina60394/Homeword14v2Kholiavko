package com.aqacources.tests.pages;

import com.aqacources.tests.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Marina on 26.02.2019.
 */
public class ProductPage extends AbstractPage {

    private String COLOR = "//ul[@id='color_to_pick_list']/li/a[@name='%s']";

    private static final String MESSAGE_EMPTY_SHOPPING_CART = "(empty)";

    //  Web elements
    @FindBy(xpath = "//div[@class='breadcrumb clearfix']")
    private WebElement breadcrumb;

    @FindBy(xpath = "//p[@id='add_to_cart']/button")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "//div[@class='button-container']/a[@title='Proceed to checkout']")
    private WebElement buttonProceedToCheckout;

    @FindBy(xpath = "//div[@id='uniform-group_1']")
    private WebElement dropdownSize;

    @FindBy(xpath = "//select[@id='group_1']")
    private WebElement selectSize;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    private WebElement buttonContinueShopping;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//div[@class='product-atributes']/a[@title='Printed Dress']")
    private WebElement productAttributes;

    @FindBy(xpath = "//a[@class='ajax_cart_block_remove_link']")
    private WebElement removeProduct;

    @FindBy(xpath = "//span[@class='ajax_cart_no_product']")
    private WebElement emptyShoppingCart;


    /**
     * Constructor
     *
     * @param testClass
     */
    public ProductPage(BaseTest testClass) {
        super(testClass);
    }

    /**
     * Click to button Add to Cart
     */
    public void clickToButtonAddToCart() {
        testClass.waitTillElementIsVisible(buttonAddToCart);
        buttonAddToCart.click();
    }

    /**
     * Click to button Proceed Checkout
     *
     * @return new instance of Checkout page
     */
    public CheckoutPage clickButtonProceedToCheckout() {
        testClass.waitTillElementIsVisible(buttonProceedToCheckout);
        buttonProceedToCheckout.click();
        return new CheckoutPage(testClass);
    }

    /**
     * Select Color
     *
     * @param color
     */
    public void selectColor(String color) {
        testClass.waitTillElementIsVisible(
                testClass.getDriver().findElement(By.xpath(String.format(COLOR, color))));

        testClass.getDriver().findElement(By.xpath(String.format(COLOR, color))).click();
    }

    /**
     * Select Size
     *
     * @param size
     */
    public void selectSize(String size) {
        testClass.waitTillElementIsVisible(dropdownSize);

        dropdownSize.click();

        Select dropdownSize = new Select(this.selectSize);

        dropdownSize.selectByVisibleText(size);
    }

    /**
     * Click to button Continue Shopping
     */
    public void clickToButtonContinueShopping() {
        testClass.waitTillElementIsVisible(buttonContinueShopping);
        buttonContinueShopping.click();
    }

    /**
     * Move to Shopping Cart
     */
    public void moveToShoppingCart() {
        testClass.waitTillElementIsVisible(shoppingCart);

        Actions actions = new Actions(testClass.getDriver());

        // Move to element
        actions.moveToElement(shoppingCart).build().perform();

    }

    /**
     * Check Product Attributes
     *
     * @param color
     * @param size
     */
    public void checkProductAttributes(String color, String size) {
        testClass.waitTillElementIsVisible(productAttributes);

        String productAtributes = productAttributes.getText();
        String expectedAttributes = color + ", " + size;

        Assert.assertEquals(productAtributes, expectedAttributes);
    }

    /**
     * Delete Product From Shopping Cart
     */
    public void deleteProductFromShoppingCart() {
        testClass.waitTillElementIsVisible(removeProduct);

        removeProduct.click();
    }

    /**
     * Check empty shopping cart
     */
    public void checkEmptyShoppingCart() {
        testClass.waitTillElementIsVisible(emptyShoppingCart);

        Assert.assertEquals(MESSAGE_EMPTY_SHOPPING_CART, emptyShoppingCart.getText());
    }

}
