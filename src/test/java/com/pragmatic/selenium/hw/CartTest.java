package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {
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

    @Test
    public void testVerifyCartProduct(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");

        // Find the Sauce Labs Bike Light
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        // Verify that the actual product in the cart matches the expected product
        Assert.assertEquals(webDriver.findElement(By.id("item_0_title_link")).getText(), "Sauce Labs Bike Light",
                "Product mismatch in cart");
    }
}
