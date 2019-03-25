package com.aqacources.tests.tests;

import com.aqacources.tests.base.BaseTest;
import com.aqacources.tests.pages.*;
import org.junit.Test;

/**
 * Created by Marina on 21.03.2019.
 */
public class CheckProductCart extends BaseTest {

    private String MENU_WOMEN = "Women";

    private String MENU_SUBCATEGORY = "Evening Dresses";

    private String PRODUCT_NAME = "Printed Dress";

    private String COLOR = "Pink";

    private String SIZE = "M";


    @Test
    public void checkProductCart() {
        // Initialize Home Page
        HomePage homePage = openSite();
        log("Opened site");

        // Click on Sign In link
        LoginPage loginPage = homePage.clickSignInLink();
        log("Clicked Sgn In");

        // Sign In with login and password
        MyAccountPage myAccountPage = loginPage.signIn();
        log("Signed In on the site");

        // Move To menu Women
        myAccountPage.moveToElementMenu(MENU_WOMEN);
        log("Moved to menu Dresses");

        // Click to subcategory Evening Dresses
        ProductListPage productListPage = myAccountPage.clickToSubCategory(MENU_SUBCATEGORY);
        log("Clicked to subcategory Evening Dresses");

        // Open in new window and switch to window
        ProductPage productPage = productListPage.openInNewTabAndSwitchToWindow(PRODUCT_NAME);
        log("Opened and Switched to New window");

        // Select color Pink
        productPage.selectColor(COLOR);
        log("Selected Color");

        // Select Size M
        productPage.selectSize(SIZE);
        log("Selected Size");

        // Click to button Add to Cart
        productPage.clickToButtonAddToCart();
        log("Clicked to button Add to Cart");

        // Click to button Continue Shopping
        productPage.clickToButtonContinueShopping();
        log("Clicked to button Continue Shopping");

        // Move to Shopping Cart
        productPage.moveToShoppingCart();
        log("Moved to Shopping Cart");

        // Check Product Attributes
        productPage.checkProductAttributes(COLOR, SIZE);
        log("Checked Product Attributes");

        // Delete Product From Shopping Cart
        productPage.deleteProductFromShoppingCart();
        log("Deleted Product From Shopping Cart");

        // Check Empty Shopping Cart
        productPage.checkEmptyShoppingCart();
        log("Checked Empty Shopping Cart");

        // Close child tab and switch to parent
        productPage.closedChildTabAndSwitchToParentWindow();
        log("Closed child tab and switched To Parent Tab");

        // Get cookies
        productPage.getCookies();
        log("Got cookies");

    }

}
