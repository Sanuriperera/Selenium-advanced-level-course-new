package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.DataProviderSourceLab;
import com.pragmatic.selenium.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceCheckoutWithPageFactoryTest extends BaseClass{

    @Test(dataProvider="user-information",dataProviderClass = DataProviderSourceLab.class,
            description = "Test Case 4.1: Verify the First Name,Last Name, and Postal Code fields are required on the checkout information page.")
    public void testRequiredFields(String fname,String lname,String postalCode,String expectedMessage){
        // Find the Sauce Labs Bike Light and added to the cart
        SaProductListPage productListPage=new SaProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        //Navigate to the cart page
        productListPage.clickCartIcon();
        SaCartPage cartPage=new SaCartPage(webDriver);
        //Select checkout button
        cartPage.clickCheckoutBtn();
        //Enter details in checkout
        SaCheckoutPage checkoutPage=new SaCheckoutPage(webDriver);
        checkoutPage.typeFirstName(fname).typeLastName(lname).typePostalCode(postalCode).clickContinue();
        //Get the error message
        String errorMessage=checkoutPage.getError();
        Assert.assertEquals(errorMessage, expectedMessage,"Error message is incorrect");
    }


    @Test(description = "Test Case 4.2: Verify entering valid information on the checkout information page allows proceeding to the next step.")
    public void testCheckoutWithValidInformation(){
        SaProductListPage productListPage=new SaProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        //Navigate to the cart page
        productListPage.clickCartIcon();
        SaCartPage cartPage=new SaCartPage(webDriver);
        //Select checkout button
        cartPage.clickCheckoutBtn();
        //Enter details in checkout
        SaCheckoutPage checkoutPage=new SaCheckoutPage(webDriver);
        checkoutPage.typeFirstName("sanuri").typeLastName("perera").typePostalCode("10400").clickContinue();
        // Verify that the page has transitioned to the next step (e.g., overview page)
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Checkout did not proceed to the next step");
        // Additional assertions to verify Checkout: Overview is available
        SaOverviewPage overviewPage=new SaOverviewPage(webDriver);
        String errorMessage=overviewPage.getTitleText();
        Assert.assertEquals(errorMessage,"Checkout: Overview", "Expected 'Checkout: Overview' text not found");
    }

    @Test(description = "Test Case 4.4: Verify the Finish button completes the order and displays the confirmation message.")
    public void testFinishButton(){
        SaProductListPage productListPage=new SaProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        //Navigate to the cart page
        productListPage.clickCartIcon();
        SaCartPage cartPage=new SaCartPage(webDriver);
        //Select checkout button
        cartPage.clickCheckoutBtn();
        //Enter details in checkout
        SaCheckoutPage checkoutPage=new SaCheckoutPage(webDriver);
        checkoutPage.typeFirstName("sanuri").typeLastName("perera").typePostalCode("10400").clickContinue();
        //Select finished button
        SaOverviewPage overviewPage=new SaOverviewPage(webDriver);
        overviewPage.clickFinish();
        // Check the page text in order completed page
        SaCheckoutCompletePage completePage=new SaCheckoutCompletePage(webDriver);
        Assert.assertEquals(completePage.getPageText(),"Checkout: Complete!",
                "finished button did not proceed to order complete page");
        // Check the order directed to order completed page
        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "finished button did not proceed to order complete page");
    }


    @Test(description = "Test Case 4.5: Verify the Cancel button navigates back to the cart page.")
    public void testCancelButton(){
        SaProductListPage productListPage=new SaProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        //Navigate to the cart page
        productListPage.clickCartIcon();
        SaCartPage cartPage=new SaCartPage(webDriver);
        //Select checkout button
        cartPage.clickCheckoutBtn();
        //Select cancel button
        SaCheckoutPage checkoutPage=new SaCheckoutPage(webDriver);
        checkoutPage.clickCancel();
        // Verify that the page has transitioned to the cart  page
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "cancel button did not proceed to cart page");
    }
}
