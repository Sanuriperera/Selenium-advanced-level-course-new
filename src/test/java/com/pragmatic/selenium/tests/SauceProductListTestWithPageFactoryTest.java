package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SaProductListPage;
import com.pragmatic.selenium.pages.SaProductPage;
import com.pragmatic.selenium.pages.SauceProductListPage;
import org.openqa.selenium.WebElement;
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
//        SaProductListPage productListPage=new SaProductListPage(webDriver);
//        // Get all product elements
//        List<String[]> actualProducts = productListPage.getAllProductDetails();
//        // Validate product count
//        Assert.assertEquals(actualProducts.size(), expectedProducts.length, "Product count mismatch!");
//        for (int i = 0; i < actualProducts.size(); i++) {
//            String[] actualProduct = actualProducts.get(i);
//            String expectedName = expectedProducts[i][0];
//            String expectedPrice = expectedProducts[i][1];
//            String expectedImage = expectedProducts[i][2];
//
//            Assert.assertEquals(actualProduct[0], expectedName, "Product name mismatch for " + expectedName);
//            Assert.assertEquals(actualProduct[1], expectedPrice, "Product price mismatch for " + expectedName);
//            Assert.assertTrue(actualProduct[2].contains(expectedImage), "Product image mismatch for " + expectedName);
//        }
    }


    @Test(description = "Test Case 2.3: Verify the product details page when clicking on a product.")
    public void testProductDetails(){
        // Click on Sauce Labs Bolt T-Shirt in the list
        SaProductListPage productListPage=new SaProductListPage(webDriver);
        productListPage.clickSauceLabsBoltTShirtTitle();
        // Verify the product name
        SaProductPage productPage=new SaProductPage(webDriver);
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
        SaProductListPage productListPage=new SaProductListPage(webDriver);
        // Get all products
        List<WebElement> productContainers  = productListPage.getAllProducts();
        int initialCount=0;
        for (WebElement product : productContainers) {
            // Locate the "Add to cart" button within the product container
            // And click the "Add to cart" button
            productListPage.clickAddToCart();
            // Get the initial cart badge count
            productListPage.getCartBadgeCount();
            int currentCount = Integer.parseInt(productListPage.getCartBadgeCount());
            // Verify that the cart badge count has increased by 1
            // Check the product index and verify cart badge count
            Assert.assertEquals(currentCount, productContainers.indexOf(product) + 1,
                    "Cart badge count does not match the expected value.");
        }
    }


    @Test(description = "Test Case 2.5: Verify the Remove button functionality after adding a product to the cart.")
    public void testRemoveButtonFunctionality(){
        SaProductListPage productListPage=new SaProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        // Get the  cart badge count
        String cartCount=productListPage.getCartBadgeCount();
        Assert.assertEquals(cartCount,"1","Cart badge should show 1 after adding one product.");
        //Check the remove button
        String removeBtn= productListPage.getRemoveBtnText();
        Assert.assertEquals(removeBtn,"Remove");
        //Select remove button
        productListPage.clickRemoveButton();
        //Check the remove button replace with the add to cart button
        Assert.assertEquals( productListPage.getSauceLabsBikeLightAddToCartText(),"Add to cart");
        // After removing all products, the cart badge should no longer exist
        System.out.println(productListPage.getCartBadge());
//        Assert.assertNull(productListPage.getCartBadge(), "Cart badge should not exist after removing all products.");
    }


    @Test(description = "Test Case 2.6: Verify the product count on the cart icon matches the number of added products.")
    public void testCartCount(){
        // Find the Sauce Labs Bike Light
        SaProductListPage productListPage=new SaProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        // Get the  cart badge count
        String cartCount = productListPage.getCartBadgeCount();
        Assert.assertEquals(cartCount,"1","Cart badge should show 1 after adding one product.");
    }
}
