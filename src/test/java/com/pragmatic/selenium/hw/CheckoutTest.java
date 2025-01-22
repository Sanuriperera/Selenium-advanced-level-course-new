package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutTest {
    WebDriver webDriver;
    @BeforeMethod
    public void openLoginPage(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
    }

//    @AfterMethod
//    public void closeBrowser(){
//        webDriver.quit();
//    }
    @Test(dataProvider="user-information")
    public void testRequiredFields(String fname,String lname,String postalCode,String expectedMessage){
        //Test Case 3.1:Verify that the correct products are displayed in the cart after adding them from the product listing page.
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        // Find the Sauce Labs Bike Light and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        //Select Continue Shopping button
        webDriver.findElement(By.id("checkout")).click();
        //Select continue button
        webDriver.findElement(By.id("continue")).click();
        //Get the error message
        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        //Select Continue Shopping button
        webDriver.findElement(By.id("first-name")).sendKeys(fname);
        webDriver.findElement(By.id("last-name")).sendKeys(lname);
        webDriver.findElement(By.id("postal-code")).sendKeys(postalCode);

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
}
