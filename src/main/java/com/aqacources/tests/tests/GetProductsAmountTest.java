package com.aqacources.tests.tests;

import com.aqacources.tests.base.BaseTest;
import com.aqacources.tests.pages.*;
import org.junit.Test;

/**
 * Created by Marina on 24.02.2019.
 */
public class GetProductsAmountTest extends BaseTest {

    @Test
    public void testGetProductAmount() {

        // Initialize Home Page
        HomePage homePage = openSite();
        log("Opened site");

        LoginPage loginPage = homePage.clickSignInLink();
        log("Clicked Sgn In");

        // Sign In with login and password
        MyAccountPage myAccountPage = loginPage.signIn();
        log("Signed in on the site");

        // click to Menu Dresses
        DressesPage dressesPage = myAccountPage.clickMenuDresses();
        log("Clicked menu Dresses");

        //  Click to Summer Dresses
        SummerDressesPage summerDressesPage = dressesPage.clickMenuSummerDresses();
        log("Clicked Summer Dresses");

        // compare product amount from message and products
        summerDressesPage.compareProductAmounts();
        log("Compared summer dresses amounts from the page and from the message");

        // click to filter color White
        summerDressesPage.clickFilterColorWhite();
        log("CLicked to Filter color White");

        // compare product amount from message and products
        summerDressesPage.compareProductAmounts();
        log("Compared summer white dresses from the page and from the message");

        closeSite();
    }
}
