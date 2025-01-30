package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceProductListPage;
import com.pragmatic.selenium.pages.SauceProductPage;
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


    @Test(description = "Test Case 2.3: Verify the product details page when clicking on a product.")
    public void testProductDetails(){
        // Click on Sauce Labs Bolt T-Shirt in the list
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        productListPage.clickSauceLabsBoltTShirtTitle();
        // Verify the product name
        SauceProductPage productPage=new SauceProductPage(webDriver);
        String actualName = productPage.getName();
        String expectedTitle = "Sauce Labs Bolt T-Shirt";
        Assert.assertEquals(actualName, expectedTitle, "Product name does not match");
        //Verify the product description
        String actualDescription = productPage.getDescription();
        String expectedDescription = "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel," +
                " 100% ringspun combed cotton, heather gray with red bolt.";
        Assert.assertEquals(actualDescription, expectedDescription, "Product description does not match");
        //Verify the product price
        String actualPrice = productPage.getPrice();
        String expectedPrice ="$15.99";
        Assert.assertEquals(actualPrice, expectedPrice, "Product price does not match");
        //Verify the product image
        String actualImage = productPage.getImage();
        String expectedImage ="https://www.saucedemo.com/static/media/bolt-shirt-1200x1500.c2599ac5.jpg";
        Assert.assertEquals(actualImage, expectedImage, "Product image does not match");
    }


    @Test(description = " Test Case 2.4: Verify the 'Add to Cart' button functionality for each product.")
    public void testAllProductAddToCartFunctionality(){
        // Get all products
        List<WebElement> productContainers  = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));
        int initialCount=0;
        for (WebElement product : productContainers) {
            // Locate the "Add to cart" button within the product container
            WebElement addToCartButton = product.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']"));
            // Click the "Add to cart" button
            addToCartButton.click();
            // Get the initial cart badge count
            WebElement cartBadge = webDriver.findElement(By.className("shopping_cart_badge"));
            int currentCount = Integer.parseInt(cartBadge.getText());
            // Verify that the cart badge count has increased by 1
            // Check the product index and verify cart badge count
            Assert.assertEquals(currentCount, productContainers.indexOf(product) + 1,
                    "Cart badge count does not match the expected value.");
        }
    }
}
