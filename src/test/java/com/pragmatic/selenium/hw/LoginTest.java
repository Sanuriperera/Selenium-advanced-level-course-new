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
        //Test Case 1.1: Verify login with valid username and password.
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public  void testLoginWithInvalidUsername(){
        //Test Case 1.2: Verify login with an invalid username.
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
        //Test Case 1.3: Verify login with an invalid password.
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("Secret_Sauce");
        webDriver.findElement(By.id("login-button")).click();
        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service",
                "Error message is incorrect");
    }

    @Test
    public  void testLoginWithBlankCredentials(){
        //Test Case 1.4: Verify login with empty username and password fields.
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();
        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required","Error message is incorrect");
    }

    @Test
    public void testLoginWithLockedOutUserCredentials(){
        //Test Case 1.5: Verify login with locked-out user credentials.
        webDriver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        String errorMessage= webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Sorry, this user has been locked out.",
                "Error message is incorrect");
    }

    @Test
    public void testLoginWithPerformanceGlitchUserCredentials(){
        //Test Case 1.6: Verify login with performance glitch user credentials.
        webDriver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public void testPlaceholderVerification(){
        //Test Case 1.7: Verify if the `Username` and `Password` fields display placeholders properly.
        Assert.assertEquals(webDriver.findElement(By.id("user-name")).getAttribute("placeholder"),"Username",
                "Username placeholder does not match");
        Assert.assertEquals(webDriver.findElement(By.id("password")).getAttribute("placeholder"),"Password",
                "Password placeholder does not match");
    }
}
