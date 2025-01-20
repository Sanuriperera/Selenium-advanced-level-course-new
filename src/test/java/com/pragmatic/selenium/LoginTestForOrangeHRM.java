package com.pragmatic.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestForOrangeHRM {
    WebDriver webDriver;
    @BeforeMethod
    public void openLoginPage(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();

    }

    @AfterMethod
    public void closeBrowser(){
        webDriver.quit();
    }

    @Test
    public void testCurrentURL(){
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith("https://opensource-demo.orangehrmlive.com/"));
    }

    @Test
    public void testPageTitle(){
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(webDriver.getTitle(),"OrangeHRM");
    }

    @Test
    public void testNavigation(){

    }

}
