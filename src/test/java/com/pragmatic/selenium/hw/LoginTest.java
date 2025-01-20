package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
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
    public void testLoginWithStandardUserCredentials(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public  void testLoginWithInvalidUsername(){
        webDriver.findElement(By.id("user-name")).sendKeys("Standard_User");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is incorrect");
    }

    @Test
    public  void testLoginWithInvalidPassword(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("Secret_Sauce");
        webDriver.findElement(By.id("login-button")).click();

        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service",
                "Error message is incorrect");
    }

    @Test
    public  void testLoginWithBlankCredentials(){
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();

        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required","Error message is incorrect");
    }

    @Test
    public void testLoginWithLockedOutUserCredentials(){
        webDriver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        String errorMessage= webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Sorry, this user has been locked out.",
                "Error message is incorrect");
    }

    @Test
    public void testLoginWithPerformanceGlitchUserCredentials(){
        webDriver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public void testPlaceholderVerification(){
        Assert.assertEquals(webDriver.findElement(By.id("user-name")).getAttribute("placeholder"),"Username",
                "Username placeholder does not match");
        Assert.assertEquals(webDriver.findElement(By.id("password")).getAttribute("placeholder"),"Password",
                "Password placeholder does not match");
    }

}
