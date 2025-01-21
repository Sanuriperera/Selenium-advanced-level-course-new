package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        // Verify that the actual product in the cart matches the expected product description
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='inventory-item-desc']")).getText(),
                "A red light isn't the desired state in testing but it sure helps when riding your bike at night." +
                        " Water-resistant with 3 lighting modes, 1 AAA battery included.",
                "Product mismatch in cart");
    }

    @Test
    public void testRemoveFromCart(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        // Initial check: check the cart badge should not exist before adding products
        WebElement cartBadge = getCartBadge();
        Assert.assertNull(cartBadge, "Cart badge should not exist before adding any product.");
        // Find the Sauce Labs Bike Light
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

    }

    // Helper method to get cart badge element
    private WebElement getCartBadge() {
        try {
            return webDriver.findElement(By.cssSelector("[data-test=shopping-cart-badge]"));
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }

    @Test
    public void testContinueShoppingButtonNavigation(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        // Find the Sauce Labs Bike Light
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        //Select Continue Shopping button
        webDriver.findElement(By.id("continue-shopping")).click();
        // Assert user is redirected to product listing page
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void testCheckoutButtonNavigation(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        // Find the Sauce Labs Bike Light
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        //Navigate to the cart page
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        //Select Continue Shopping button
        webDriver.findElement(By.id("checkout")).click();
        // Assert user is redirected to product listing page
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-one.html"));
    }
}
