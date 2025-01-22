package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductListingPageTest {
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
    public void testAllProductDetails(){
        //Test Case 2.1: Verify if all products are displayed with the correct name, price, and image.
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        // Expected product details (using a 2D array)
        String[][] expectedProducts = {
                {"Sauce Labs Backpack", "$29.99", "sauce-backpack-1200x1500.0a0b85a3.jpg"},
                {"Sauce Labs Bike Light", "$9.99", "bike-light-1200x1500.37c843b0.jpg"},
                {"Sauce Labs Bolt T-Shirt", "$15.99", "bolt-shirt-1200x1500.c2599ac5.jpg"},
                {"Sauce Labs Fleece Jacket", "$49.99", "sauce-pullover-1200x1500.51d7ffaf.jpg"},
                {"Sauce Labs Onesie", "$7.99", "red-onesie-1200x1500.2ec615b2.jpg"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99", "red-tatt-1200x1500.30dadef4.jpg"}
        };
        // Get all product elements
        List<WebElement> productElements = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));
        // Verify each product details
        for (int i = 0; i < productElements.size(); i++) {
            WebElement product = productElements.get(i);
            // Get actual product details
            String actualName = product.findElement(By.className("inventory_item_name")).getText();
            String actualPrice = product.findElement(By.className("inventory_item_price")).getText();
//            String actualImage = product.findElement(By.xpath("//div[@class= 'inventory_item_img']/a/img")).getDomProperty("src");
            // Get expected values from the 2D array
            String expectedName = expectedProducts[i][0];
            String expectedPrice = expectedProducts[i][1];
//            String expectedImage = expectedProducts[i][2];
            // Verify the product name,price,image
            Assert.assertEquals(actualName,expectedName,"Product name mismatch for " + expectedName);
            Assert.assertEquals(actualPrice,expectedPrice,"Product price mismatch for " + expectedName);
//            Assert.assertEquals(actualImage,expectedImage,"Product price mismatch for " + expectedName);
        }
    }

    @Test
    public void testProductDetails(){
        //Test Case 2.3: Verify the product details page when clicking on a product.
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        // Click on Sauce Labs Bolt T-Shirt in the list
        webDriver.findElement(By.id("item_1_title_link")).click();
        // Verify the product name
        String actualName = webDriver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText();
        String expectedTitle = "Sauce Labs Bolt T-Shirt";
        Assert.assertEquals(actualName, expectedTitle, "Product name does not match");
        //Verify the product description
        String actualDescription = webDriver.findElement(By.cssSelector("[data-test='inventory-item-desc']")).getText();
        String expectedDescription = "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel," +
                " 100% ringspun combed cotton, heather gray with red bolt.";
        Assert.assertEquals(actualDescription, expectedDescription, "Product description does not match");
        //Verify the product price
        String actualPrice = webDriver.findElement(By.cssSelector("[data-test='inventory-item-desc']")).getText();
        String expectedPrice ="$15.99";
        Assert.assertEquals(actualDescription, expectedDescription, "Product price does not match");
        //Verify the product price
        String actualImage = webDriver.findElement(By.cssSelector("[data-test='item-sauce-labs-bolt-t-shirt-img']")).getAttribute("src");
        String expectedImage ="https://www.saucedemo.com/static/media/bolt-shirt-1200x1500.c2599ac5.jpg";
        Assert.assertEquals(actualDescription, expectedDescription, "Product image does not match");
    }

    @Test
    public void testAllProductAddToCartFunctionality(){
//        Test Case 2.4: Verify the "Add to Cart" button functionality for each product.
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");

    }
}
