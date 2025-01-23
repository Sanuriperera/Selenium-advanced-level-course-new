package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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

    @Test
    public void testAllProductDetails(){
        //Test Case 2.1: Verify if all products are displayed with the correct name, price, and image.
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
        Assert.assertEquals(actualPrice, expectedPrice, "Product price does not match");
        //Verify the product price
        String actualImage = webDriver.findElement(By.cssSelector("[data-test='item-sauce-labs-bolt-t-shirt-img']")).getAttribute("src");
        String expectedImage ="https://www.saucedemo.com/static/media/bolt-shirt-1200x1500.c2599ac5.jpg";
        Assert.assertEquals(actualImage, expectedImage, "Product image does not match");
    }

    @Test
    public void testAllProductAddToCartFunctionality(){
//        Test Case 2.4: Verify the "Add to Cart" button functionality for each product.
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

    @Test
    public void testRemoveButtonFunctionality(){
        //Test Case 2.5: Verify the "Remove" button functionality after adding a product to the cart.
        // Find the Sauce Labs Bike Light
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        // Get the  cart badge count
        String cartCount = webDriver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount,"1","Cart badge should show 1 after adding one product.");
        //Check the remove button
        String removeBtn= webDriver.findElement(By.cssSelector("[data-test='remove-sauce-labs-bike-light']")).getText();
        Assert.assertEquals(removeBtn,"Remove");
        //Select remove button
        webDriver.findElement(By.cssSelector("[data-test='remove-sauce-labs-bike-light']")).click();
        //Check the remove button replace with the add to cart button
        Assert.assertEquals(webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).getText(),"Add to cart");
        // After removing all products, the cart badge should no longer exist
        WebElement cartBadge = getCartBadge();
        Assert.assertNull(cartBadge, "Cart badge should not exist after removing all products.");
    }

    // Method to get the cart badge element
    private WebElement getCartBadge() {
        System.out.println("The cart function");
        try {
            return webDriver.findElement(By.cssSelector(".shopping_cart_badge"));
        } catch (Exception e) {
            return null; // Return null if the badge does not exist
        }
    }

    @Test
    public void testCartCount(){
        //Test Case 2.6: Verify the product count on the cart icon matches the number of added products.
        // Find the Sauce Labs Bike Light
        webDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        // Get the  cart badge count
        String cartCount = webDriver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount,"1","Cart badge should show 1 after adding one product.");
    }
}
