package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.DataProviderSourceLab;
import com.pragmatic.selenium.pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
        loginPage.typeUserName("standard_user").typePassword("secret_sauce").clickLogin();
    }

    @Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSourceLab.class,
            description = "Test Case 1: Verify login with invalid credentials")
    public  void testInvalidUserLoginDDTFromClass(String username,String password,String expectedError){
        SauceLoginPage loginPage=new SauceLoginPage(webDriver);
        loginPage.typeUserName(username).typePassword(password).clickLogin();
        Assert.assertEquals(loginPage.getError(),expectedError);
    }


}
