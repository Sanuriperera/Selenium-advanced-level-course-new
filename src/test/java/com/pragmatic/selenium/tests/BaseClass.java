package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        testLoginWithStandardUserCredentials();
    }


    @AfterMethod
    public void closeBrowser(){
        webDriver.quit();
    }


    public void testLoginWithStandardUserCredentials(){
        SauceLoginPage loginPage=new SauceLoginPage(webDriver);
        loginPage.typeUserName("standard_user").typePassword("secret_sauce").clickLogin();
    }
}
