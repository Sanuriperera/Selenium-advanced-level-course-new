package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CheckoutTest {
    WebDriver webDriver;
    @BeforeMethod
    public void openLoginPage(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        //Log in with valid credentials
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @AfterMethod
    public void closeBrowser(){
        webDriver.quit();
    }

    @Test(dataProvider="user-information")
    public void testRequiredFields(String fname,String lname,String postalCode,String expectedMessage){
        //Test Case 4.1: Verify the "First Name," "Last Name," and "Postal Code" fields are required on the checkout information page.
        // Find the Sauce Labs Bike Light and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        //Select checkout button
        webDriver.findElement(By.id("checkout")).click();
        //Enter details in checkout
        webDriver.findElement(By.id("first-name")).sendKeys(fname);
        webDriver.findElement(By.id("last-name")).sendKeys(lname);
        webDriver.findElement(By.id("postal-code")).sendKeys(postalCode);
        //Select continue button
        webDriver.findElement(By.id("continue")).click();
        //Get the error message
        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage, expectedMessage,"Error message is incorrect");
    }

    @DataProvider(name="user-information")
    public Object[][] userDetails() {
        return new Object[][]{
                {"","","", "Error: First Name is required"},
                {"","perera","10400", "Error: First Name is required"},
                {"sanuri","", "10400","Error: Last Name is required"},
                {"sanuri","perera","", "Error: Postal Code is required"}
        };
    }

    @Test
    public void testCheckoutWithValidInformation(){
        //Test Case 4.2: Verify entering valid information on the checkout information page allows proceeding to the next step.
        // Find the Sauce Labs Bike Light and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        //Select checkout button
        webDriver.findElement(By.id("checkout")).click();
        //Select Continue Shopping button
        webDriver.findElement(By.id("first-name")).sendKeys("Sanuri");
        webDriver.findElement(By.id("last-name")).sendKeys("Perera");
        webDriver.findElement(By.id("postal-code")).sendKeys("10400");
        //Select continue button
        webDriver.findElement(By.id("continue")).click();
        // Verify that the page has transitioned to the next step (e.g., overview page)
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "Checkout did not proceed to the next step");
        // Additional assertions to verify Checkout: Overview is available
        String errorMessage=webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
        Assert.assertEquals(errorMessage,"Checkout: Overview", "Expected 'Checkout: Overview' text not found");
    }

    @Test
    public void testOrderSummary(){
        //Test Case 4.3: Verify the order summary page displays the correct list of items, prices, and total.
        // Find the Sauce Labs Bike Light and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        //Select checkout button
        webDriver.findElement(By.id("checkout")).click();
        //Select Continue Shopping button
        webDriver.findElement(By.id("first-name")).sendKeys("Sanuri");
        webDriver.findElement(By.id("last-name")).sendKeys("Perera");
        webDriver.findElement(By.id("postal-code")).sendKeys("10400");
        //Select continue button
        webDriver.findElement(By.id("continue")).click();
        // Get the expected list of items
        // Expected product details (using a 2D array)
        String[][] expectedProducts = {
                {"Sauce Labs Bike Light", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "$15.99"},
        };
        // Get all product elements
        List<WebElement> productElements = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));
        // Verify each product details
        for (int i = 0; i < productElements.size(); i++) {
            WebElement product = productElements.get(i);
            // Get actual product details
            String actualName = product.findElement(By.className("inventory_item_name")).getText();
            String actualPrice = product.findElement(By.className("inventory_item_price")).getText();
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

    @Test
    public void testFinishButton(){
        //Test Case 4.4: Verify the "Finish" button completes the order and displays the confirmation message.
        // Find the Sauce Labs Bike Light and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        //Select checkout button
        webDriver.findElement(By.id("checkout")).click();
        //Select Continue Shopping button
        webDriver.findElement(By.id("first-name")).sendKeys("Sanuri");
        webDriver.findElement(By.id("last-name")).sendKeys("Perera");
        webDriver.findElement(By.id("postal-code")).sendKeys("10400");
        //Select continue button
        webDriver.findElement(By.id("continue")).click();
        //Select finished button
        webDriver.findElement(By.id("finish")).click();
        // Check the page text in order completed page
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Checkout: Complete!",
                "finished button did not proceed to order complete page");
        // Check the order directed to order completed page
        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "finished button did not proceed to order complete page");
    }

    @Test
    public void testCancelButton(){
        //Test Case 4.5: Verify the "Cancel" button navigates back to the cart page.
        // Find the Sauce Labs Bike Light and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        //Select checkout button
        webDriver.findElement(By.id("checkout")).click();
        //Select cancel button
        webDriver.findElement(By.id("cancel")).click();
        // Verify that the page has transitioned to the cart  page
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedUrl, "cancel button did not proceed to cart page");
    }
}
