package com.aqacources.tests.tests;

import com.aqacources.tests.base.BaseTest;
import com.aqacources.tests.enums.CategoriesBreadcrumb;
import com.aqacources.tests.pages.*;
import org.junit.Test;

/**
 * Created by Marina on 26.02.2019.
 */
public class AddToCartTest extends BaseTest {

    private static final String FADED_SHORT_SLEEVE_TSHIRTS = "Faded Short Sleeve T-shirts";

    @Test
    public void testAddToCart() {

        // Initialize Home Page
        HomePage homePage = openSite();
        log("Opened site");

        // Click on Sign In link
        LoginPage loginPage = homePage.clickSignInLink();
        log("Clicked Sgn In");

        // Sign In with login and password
        MyAccountPage myAccountPage = loginPage.signIn();
        log("Signed In on the site");

        // Click menu T-Shirts
        ProductListPage productListPage = myAccountPage.clickMenuTShirt();
        log("Clicked menu T-Shirts");

        //  Click to Product
        ProductPage productPage = productListPage.clickToProduct(FADED_SHORT_SLEEVE_TSHIRTS);
        log("Clicked to Product Container");

        // Check breadcrumb
        String breadCrumbsForProductPage =
                CategoriesBreadcrumb.WOMEN.getName()
                        + ' '
                        + CategoriesBreadcrumb.TOPS.getName()
                        + ' '
                        + CategoriesBreadcrumb.TSHIRTS.getName()
                        + ' '
                        + FADED_SHORT_SLEEVE_TSHIRTS;
        productPage.checkBreadrumb(breadCrumbsForProductPage);
        log("Checked breadcrumb");

        // Click on button Add to Cart
        productPage.clickToButtonAddToCart();
        log("Clicked to button Add to Cart");

        // Click Button Proceed Checkout
        CheckoutPage checkoutPage = productPage.clickButtonProceedToCheckout();
        log("Clicked Proceed to Checkout");

        // Click to Increase Quantity
        checkoutPage.clickToIncreaseQuantity();
        log("Increased Quantity");

        // Check Total Price
        checkoutPage.checkTotalPrice();
        log("Checked amount price");

        // Click Delete Product
        checkoutPage.clickDeleteProduct();
        log("Deleted Product");

        // Check Empty Cart
        checkoutPage.checkEmptyCart();
        log("Checked that we have message: Your shopping cart is empty");

        closeSite();
    }
}
