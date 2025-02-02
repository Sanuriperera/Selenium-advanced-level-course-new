package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SauceProductListTestWithPageFactoryTest extends BaseClass{
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
        List<String[]> actualProducts = productListPage.getAllProductDetails();
        // Validate product count
        Assert.assertEquals(actualProducts.size(), expectedProducts.length, "Product count mismatch!");
        for (int i = 0; i < actualProducts.size(); i++) {
            String[] actualProduct = actualProducts.get(i);
            String expectedName = expectedProducts[i][0];
            String expectedPrice = expectedProducts[i][1];
            String expectedImage = expectedProducts[i][2];

            Assert.assertEquals(actualProduct[0], expectedName, "Product name mismatch for " + expectedName);
            Assert.assertEquals(actualProduct[1], expectedPrice, "Product price mismatch for " + expectedName);
            Assert.assertTrue(actualProduct[2].contains(expectedImage), "Product image mismatch for " + expectedName);
        }
    }
}
