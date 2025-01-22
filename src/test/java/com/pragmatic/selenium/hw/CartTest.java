package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void testCorrectProductsInCart(){
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
        // Get the list of product names in the cart
        List<WebElement> cartItems = webDriver.findElements(By.cssSelector("[data-test='inventory-item-name']"));
        // Expected product names
        List<String> expectedProducts = new ArrayList<>(List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"));
        // Verify that the actual products in the cart match the expected products
        Assert.assertEquals(cartItems.size(), expectedProducts.size()," Number of products in cart does not match expected");
        for (int i = 0; i < cartItems.size(); i++) {
            String actualProductName = cartItems.get(i).getText();
            String expectedProductName = expectedProducts.get(i);
            Assert.assertEquals(actualProductName, expectedProductName,"Product name mismatch at index"+i);
        }
    }

    @Test
    public void testRemoveFromCart(){
        //Test Case 3.2: Verify that removing a product from the cart updates the cart count and removes it from the cart page.
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
        // Get initial cart count
        String initialCartCountText = webDriver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText();
        int initialCartCount = Integer.parseInt(initialCartCountText);
        // Remove the "Sauce Labs Bike Light"
        webDriver.findElement(By.id("remove-sauce-labs-bike-light")).click();
        // Check if cart count has been updated
        String updatedCartCountText = webDriver.findElement(By.className("shopping_cart_badge")).getText();
        int updatedCartCount = Integer.parseInt(updatedCartCountText);
        Assert.assertEquals(updatedCartCount, initialCartCount - 1,"Cart count did not update as expected.");
        // Get the list of product names in the cart after removal
        List<WebElement> cartItems = webDriver.findElements(By.cssSelector("[data-test='inventory-item-name']"));
        // Verify that the removed product is no longer in the cart
        Assert.assertFalse(cartItems.contains("Sauce Labs Bike Light"), "Removed product is still present in the cart");
    }

    @Test
    public void testContinueShoppingButtonNavigation(){
        //Test Case 3.3: Verify the "Continue Shopping" button navigates back to the product listing page.
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
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"),"The page is mismatch");
    }

    @Test
    public void testCheckoutButtonNavigation(){
        //Test Case 3.4: Verify the "Checkout" button navigates to the checkout page.
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
