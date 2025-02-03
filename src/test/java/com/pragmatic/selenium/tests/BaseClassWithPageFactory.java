package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SaLoginPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClassWithPageFactory {
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        testLoginWithStandardUserCredentials();
    }


//    @AfterMethod
//    public void closeBrowser(){
//        webDriver.quit();
//    }


    public void testLoginWithStandardUserCredentials(){
        SaLoginPage loginPage=new SaLoginPage(webDriver);
        loginPage.typeUserName("standard_user").typePassword("secret_sauce").clickLogin();
    }
}
