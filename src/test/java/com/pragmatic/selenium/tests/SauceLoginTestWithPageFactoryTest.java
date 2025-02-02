package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.DataProviderSourceLab;
import com.pragmatic.selenium.pages.SaLoginPage;
import com.pragmatic.selenium.pages.SaProductListPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import com.pragmatic.selenium.pages.SauceProductListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLoginTestWithPageFactoryTest {
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
    public void testLoginWithInvalidPassword(){
        SaLoginPage loginPage=new SaLoginPage(webDriver);
        loginPage.typeUserName("standard_user").typePassword("secret_sauce123").clickLogin();
        Assert.assertEquals(loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testLoginWithValidUserCredentials(){
        SaLoginPage loginPage=new SaLoginPage(webDriver);
        loginPage.typeUserName("standard_user").typePassword("secret_sauce").clickLogin();
        SaProductListPage productListPage= new SaProductListPage(webDriver);
        Assert.assertEquals(productListPage.getProductText(),"Products");
    }

    @Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSourceLab.class,
            description = "Test Case 1: Verify login with invalid credentials")
    public  void testInvalidUserLoginDDTFromClass(String username,String password,String expectedError){
        SaLoginPage loginPage=new SaLoginPage(webDriver);
        loginPage.typeUserName(username).typePassword(password).clickLogin();
        Assert.assertEquals(loginPage.getError(),expectedError);
    }
}
