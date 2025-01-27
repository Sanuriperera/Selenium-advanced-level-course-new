package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLoginTest {
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
    }


    @AfterMethod
    public void closeBrowser(){
        webDriver.quit();
    }


    @Test
    public void testLoginWithValidUserCredentials(){
        SauceLoginPage loginPage=new SauceLoginPage(webDriver);
        loginPage.typeUserName("standard_user").typepasswaord("secret_sauce").clickLogin();
    }

//    @Test
//    public void

}
