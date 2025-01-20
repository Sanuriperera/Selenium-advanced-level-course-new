package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrossBrowserTest {
    WebDriver webDriver;

    @BeforeMethod
    public void openLoginPage(){

    }

    @AfterMethod
    public void closeBrowser(){
        webDriver.quit();
    }

    @Test
    public void testChromeBrowser(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //compare
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public void testChromeHeadless(){
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--headless");
        webDriver= new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //compare
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public void testFirefoxBrowser(){
        webDriver= new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //compare
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public void testFirefoxHeadless(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        webDriver= new FirefoxDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //compare
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public void testEdgeBrowser(){
        webDriver= new EdgeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //compare
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public void testEdgeHeadless(){
        EdgeOptions options= new EdgeOptions();
        options.addArguments("headless");
        webDriver= new EdgeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //compare
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");


    }

//    @Test
//    public void testSafariBrowser(){
//        webDriver= new SafariDriver();
//        webDriver.manage().window().maximize();
//        webDriver.get("https://www.saucedemo.com/");
//
//        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
//        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
//        webDriver.findElement(By.id("login-button")).click();
//
//        //compare
//        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
//    }


}
