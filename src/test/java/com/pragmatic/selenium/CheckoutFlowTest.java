package com.pragmatic.selenium;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutFlowTest {
    WebDriver webDriver;

    @BeforeClass
    public void openLoginPage(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
    }

     @Test(priority = 1)
    public void testLogin(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products",
                "invalid login");
    }

    @Test(priority = 2)
    public void testSelectProduct(){
        webDriver.findElement(By.id("item_4_title_link")).click();
        Assert.assertEquals(webDriver.findElement(By.xpath("//div[@data-test='inventory-item-name']")).getText(),
                "Sauce Labs Backpack","invalid product");

        webDriver.findElement(By.id("add-to-cart")).click();

    }

    @Test(priority = 3)
    public void testSelectCart(){
        webDriver.findElement(By.id("shopping_cart_container")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Your Cart",
                "cart page title is not matching");

        //test added products title
        // Locate the parent element containing the price
        // Extract the full text of the price
        String actualPrice = webDriver.findElement(By.className("inventory_item_price")).getText();
        // Define the expected price
        String expectedPrice = "$29.99";

        // Assert that the actual price matches the expected price
        Assert.assertEquals(actualPrice,expectedPrice, "The price does not match!");

        //Select checkout button
        WebElement checkoutBtn= webDriver.findElement(By.id("checkout"));
        checkoutBtn.click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Checkout: Your Information",
                "checkout page is not match");

    }

    @Test(priority = 4)
    public void testCheckout(){
        Faker faker = new Faker();
        webDriver.findElement(By.id("first-name")).sendKeys(faker.name().firstName());
        webDriver.findElement(By.id("last-name")).sendKeys(faker.name().lastName());
        webDriver.findElement(By.id("postal-code")).sendKeys(faker.address().zipCode());

        //select continue button
        webDriver.findElement(By.id("continue")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Checkout: Overview",
        "check out overview text is not matching in the page");

        //select finish button
        webDriver.findElement(By.id("finish")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='complete-header']")).getText(),"Thank you for your order!",
                "Thank you text is not matching in the page");
    }



//
//    @AfterClass
//    public void closeBrowser(){
//        webDriver.quit();
//    }
}
