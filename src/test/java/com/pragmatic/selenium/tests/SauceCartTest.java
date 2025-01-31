package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceCartPage;
import com.pragmatic.selenium.pages.SauceProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SauceCartTest extends BaseClass{


    @Test(description = "Verify that the correct product are displayed in the cart after adding them")
    public void testVerifyCartProduct(){
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        productListPage.clickCartIcon();
        SauceCartPage sauceCartPage=new SauceCartPage(webDriver);
        Assert.assertEquals(sauceCartPage.getSauceLabsBikeLightTitle(),"Sauce Labs Bike Light");
        String expectedDes="A red light isn't the desired state in testing but it sure helps when riding your bike at night." +
                " Water-resistant with 3 lighting modes, 1 AAA battery included.";
        Assert.assertEquals(sauceCartPage.getSauceLabsBikeLightDescription(),expectedDes);
    }


    @Test(description = "Test Case 3.1:Verify that the correct products are displayed in the cart after adding them from the product listing page.")
    public void testCorrectProductsInCart(){
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        // Find the Sauce Labs Bike Light and added to the cart
        productListPage.clickAddToCartSauceLabsBikeLight();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        //Navigate to the cart page
        productListPage.clickCartIcon();
        SauceCartPage sauceCartPage=new SauceCartPage(webDriver);
        // Get the list of product names in the cart
        List<WebElement> cartItems=sauceCartPage.getCartItems();
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


    @Test(description = "Test Case 3.2: Verify that removing a product from the cart updates the cart count and removes it from the cart page.")
    public void testRemoveFromCart(){
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        // Find the Sauce Labs Bike Light and added to the cart
        productListPage.clickAddToCartSauceLabsBikeLight();
        // Find the Sauce Labs Bolt T-Shirt and added to the cart
        productListPage.clickAddToCartSauceLabsBoltTShirt();
        productListPage.clickCartIcon();
        SauceCartPage sauceCartPage=new SauceCartPage(webDriver);
        int initialCartCount = Integer.parseInt(sauceCartPage.getInitialCartCountText());
        // Remove the "Sauce Labs Bike Light"
        sauceCartPage.clickBikeLightRemovalButton();
        int updatedCartCount = Integer.parseInt(sauceCartPage.getInitialCartCountText());
        Assert.assertEquals(updatedCartCount, initialCartCount - 1,"Cart count did not update as expected.");
        // Get the list of product names in the cart after removal
        List<WebElement> cartItems = sauceCartPage.getCartItems();
        // Verify that the removed product is no longer in the cart
        Assert.assertFalse(cartItems.contains("Sauce Labs Bike Light"), "Removed product is still present in the cart");
    }


    @Test(description = "Test Case 3.3: Verify the Continue Shopping button navigates back to the product listing page.")
    public void testContinueShoppingButtonNavigation(){
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        SauceCartPage sauceCartPage=new SauceCartPage(webDriver);
        //Select Continue Shopping button
        sauceCartPage.clickContinueShoppingButton();
        // Assert user is redirected to product listing page
        String expectedUrl="https://www.saucedemo.com/inventory.html";
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith(expectedUrl),"The page is mismatch");
    }


    @Test(description = "Test Case 3.4: Verify the Checkout button navigates to the checkout page.")
    public void testCheckoutButtonNavigation(){
        SauceProductListPage productListPage=new SauceProductListPage(webDriver);
        productListPage.clickAddToCartSauceLabsBikeLight();
        productListPage.clickCartIcon();
        SauceCartPage sauceCartPage=new SauceCartPage(webDriver);
        //Select Continue Shopping button
        sauceCartPage.clickCheckoutBtn();
        // Assert user is redirected to product listing page
        String expectedUrl="https://www.saucedemo.com/checkout-step-one.html";
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith(expectedUrl),"The page is mismatch");
    }
}
