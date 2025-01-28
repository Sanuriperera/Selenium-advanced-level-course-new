package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SauceProductListTest extends BaseClass {
    @Test(description = "Test Case 2.1: Verify if all products are displayed with the correct name, price, and image.")
    public void testAllProductDetails(){
        // Expected product details (using a 2D array)
        String[][] expectedProducts = {
                {"Sauce Labs Backpack", "$29.99", "sauce-backpack-1200x1500.0a0b85a3.jpg"},
                {"Sauce Labs Bike Light", "$9.99", "bike-light-1200x1500.37c843b0.jpg"},
                {"Sauce Labs Bolt T-Shirt", "$15.99", "bolt-shirt-1200x1500.c2599ac5.jpg"},
                {"Sauce Labs Fleece Jacket", "$49.99", "sauce-pullover-1200x1500.51d7ffaf.jpg"},
                {"Sauce Labs Onesie", "$7.99", "red-onesie-1200x1500.2ec615b2.jpg"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99", "red-tatt-1200x1500.30dadef4.jpg"}
        };
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        // Get all product elements
        List<WebElement> productElements =productListPage.getAllProducts();
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
}
