package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.DataProviderSourceLab;
import com.pragmatic.selenium.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SauceCheckoutTest extends BaseClass{

    @Test(dataProvider="user-information",dataProviderClass = DataProviderSourceLab.class,
    description = "Test Case 4.1: Verify the First Name,Last Name, and Postal Code fields are required on the checkout information page.")
    public void testRequiredFields(String fname,String lname,String postalCode,String expectedMessage){
        // Find the Sauce Labs Bike Light and added to the cart
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        //Navigate to the cart page
        productListPage.clickCartIcon();
        SauceCartPage cartPage=new SauceCartPage(webDriver);
        //Select checkout button
        cartPage.clickCheckoutBtn();
        //Enter details in checkout
        SauceCheckoutPage checkoutPage=new SauceCheckoutPage(webDriver);
        checkoutPage.typeFirstName(fname).typeLastName(lname).typePostalCode(postalCode).clickContinue();
        //Get the error message
        String errorMessage=checkoutPage.getError();
        Assert.assertEquals(errorMessage, expectedMessage,"Error message is incorrect");
    }


    @Test(description = "Test Case 4.2: Verify entering valid information on the checkout information page allows proceeding to the next step.")
    public void testCheckoutWithValidInformation(){
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        //Navigate to the cart page
        productListPage.clickCartIcon();
        SauceCartPage cartPage=new SauceCartPage(webDriver);
        //Select checkout button
        cartPage.clickCheckoutBtn();
        //Enter details in checkout
        SauceCheckoutPage checkoutPage=new SauceCheckoutPage(webDriver);
        checkoutPage.typeFirstName("sanuri").typeLastName("perera").typePostalCode("10400").clickContinue();
        // Verify that the page has transitioned to the next step (e.g., overview page)
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Checkout did not proceed to the next step");
        // Additional assertions to verify Checkout: Overview is available
        SauceOverviewPage overviewPage=new SauceOverviewPage(webDriver);
        String errorMessage=overviewPage.getTitleText();
        Assert.assertEquals(errorMessage,"Checkout: Overview", "Expected 'Checkout: Overview' text not found");
    }


    @Test(description = "Test Case 4.3: Verify the order summary page displays the correct list of items, prices, and total.")
    public void testOrderSummary(){
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        //Navigate to the cart page
        productListPage.clickCartIcon();
        SauceCartPage cartPage=new SauceCartPage(webDriver);
        //Select checkout button
        cartPage.clickCheckoutBtn();
        //Enter details in checkout
        SauceCheckoutPage checkoutPage=new SauceCheckoutPage(webDriver);
        checkoutPage.typeFirstName("sanuri").typeLastName("perera").typePostalCode("10400").clickContinue();
        // Get the expected list of items
        // Expected product details (using a 2D array)
        String[][] expectedProducts = {
                {"Sauce Labs Bike Light", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "$15.99"},
        };
        // Get all product elements
        SauceOverviewPage overviewPage=new SauceOverviewPage(webDriver);
        List<WebElement> productElements = overviewPage.getProducts();
        // Verify each product details
        for (int i = 0; i < productElements.size(); i++) {
            WebElement product = productElements.get(i);
            // Get actual product details
            // Get actual product details
            String actualName = product.findElement(By.className("inventory_item_name")).getText();
            String actualPrice = product.findElement(By.className("inventory_item_price")).getText();
//            String actualName =overviewPage.getProductName();
//            String actualPrice = overviewPage.getPrice();
            // Get expected values from the 2D array
            String expectedName = expectedProducts[i][0];
            String expectedPrice = expectedProducts[i][1];
            // Verify the product name,price,image
            Assert.assertEquals(actualName,expectedName,"Product name mismatch for " + expectedName);
            Assert.assertEquals(actualPrice,expectedPrice,"Product price mismatch for " + expectedName);
        }
        // Get the item total price
        WebElement totalItemPriceElement = webDriver.findElement(By.cssSelector("[data-test='subtotal-label']"));
        String actualItemTotalPrice = totalItemPriceElement.getText().split(": ")[1];
        // Calculate the expected total price
        double expectedItemTotalPrice = 0.0;
        for (String[] product : expectedProducts) {
            expectedItemTotalPrice += Double.parseDouble(product[1].replace("$", ""));
        }
        String expectedItemTotalPriceStr = String.format("$%.2f", expectedItemTotalPrice);
        // Verify the total price
        Assert.assertEquals(actualItemTotalPrice,expectedItemTotalPriceStr,"Total item price does not match");
        // Get the item total price from the order page
        WebElement totalPriceElement = webDriver.findElement(By.cssSelector("[data-test='total-label']"));
        String actualTotalPriceWithSign = totalPriceElement.getText().split(": ")[1];
        // Remove the dollar sign ($) from actual total price
        String actualTotalPrice = actualTotalPriceWithSign.replace("$", "");
        //Get expected total price
        String expectedItemTotalInText = expectedItemTotalPriceStr.replace("$", "");
        //convert the value to double
        double expectedItemTotal  = Double.parseDouble(expectedItemTotalInText);
        //Get Tax
        WebElement taxText = webDriver.findElement(By.cssSelector("[data-test='tax-label']"));
        String tax = taxText.getText().split(": ")[1];
        // Remove the dollar sign ($) from item tax before conversion
        String taxInText = tax.replace("$", "");
        //convert the tax value to double
        double taxInDouble  = Double.parseDouble(taxInText);
        //Calculate expected total price
        double expectedTotalPrice= expectedItemTotal+taxInDouble;
        // Round the expected total price to two decimal places
        String expectedTotalPriceNew = String.format("%.2f", expectedTotalPrice).replace("$","");
        // Verify the total price
        Assert.assertEquals(actualTotalPrice, expectedTotalPriceNew, "Actual total price does not match expected total price.");
    }



    @Test(description = "Test Case 4.4: Verify the Finish button completes the order and displays the confirmation message.")
   public void testFinishButton(){
       SauceProductListPage productListPage=new SauceProductListPage(webDriver);
       productListPage.clickAddToCartSauceLabsBikeLight();
       productListPage.clickAddToCartSauceLabsBoltTShirt();
       //Navigate to the cart page
       productListPage.clickCartIcon();
       SauceCartPage cartPage=new SauceCartPage(webDriver);
       //Select checkout button
       cartPage.clickCheckoutBtn();
       //Enter details in checkout
       SauceCheckoutPage checkoutPage=new SauceCheckoutPage(webDriver);
       checkoutPage.typeFirstName("sanuri").typeLastName("perera").typePostalCode("10400").clickContinue();
       //Select finished button
       SauceOverviewPage overviewPage=new SauceOverviewPage(webDriver);
       overviewPage.clickFinish();
       // Check the page text in order completed page
       SauceCheckoutCompletePage completePage=new SauceCheckoutCompletePage(webDriver);
       Assert.assertEquals(completePage.getPageText(),"Checkout: Complete!",
               "finished button did not proceed to order complete page");
       // Check the order directed to order completed page
       String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
       Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "finished button did not proceed to order complete page");
   }


   @Test(description = "Test Case 4.5: Verify the Cancel button navigates back to the cart page.")
   public void testCancelButton(){
       SauceProductListPage productListPage=new SauceProductListPage(webDriver);
       productListPage.clickAddToCartSauceLabsBikeLight();
       productListPage.clickAddToCartSauceLabsBoltTShirt();
       //Navigate to the cart page
       productListPage.clickCartIcon();
       SauceCartPage cartPage=new SauceCartPage(webDriver);
       //Select checkout button
       cartPage.clickCheckoutBtn();
       //Select cancel button
       SauceCheckoutPage checkoutPage=new SauceCheckoutPage(webDriver);
       checkoutPage.clickCancel();
       // Verify that the page has transitioned to the cart  page
       String expectedUrl = "https://www.saucedemo.com/cart.html";
       Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "cancel button did not proceed to cart page");
   }
}
