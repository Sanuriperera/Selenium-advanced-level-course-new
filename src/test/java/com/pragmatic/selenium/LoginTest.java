package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;

    @BeforeMethod
    public void openLoginPage(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void closeBrowser(){
        webDriver.quit();
    }

    @Test
    public void testLoginWithStandardUserCredentials(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //compare
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }

    @Test
    public  void testLoginWithBlankCredentials(){
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();

        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        //By cssSelector
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required","Error message is incorrect");
    }

    @Test
    public  void testLoginWithBlankUsername(){
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //By XPath
//        Assert.assertEquals(webDriver.findElement(By.xpath("//h3[@data-test='error']")).getText(),"Epic sadface: Username is required");
        //By cssSelector
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username is required","Error message is incorrect");
    }

    @Test
    public  void testLoginWithBlankPassword(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();

        //By XPath
//        Assert.assertEquals(webDriver.findElement(By.xpath("//h3[@data-test='error']")).getText(),"Epic sadface: Password is required");
        //By cssSelector
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Password is required");
    }

    @Test
    public  void testLoginWithInvalidUsername(){
        webDriver.findElement(By.id("user-name")).sendKeys("Standard_User");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //By cssSelector
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is incorrect");
    }

    @Test
    public  void testLoginWithInvalidPassword(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("Secret_Sauce");
        webDriver.findElement(By.id("login-button")).click();

        //By XPath
//        Assert.assertEquals(webDriver.findElement(By.xpath("//h3[@data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
        //By cssSelector
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is incorrect");
    }

    @Test
    public  void testLoginWithInvalidCredentials(){
        webDriver.findElement(By.id("user-name")).sendKeys("Standard_User");
        webDriver.findElement(By.id("password")).sendKeys("Secret_Sauce");
        webDriver.findElement(By.id("login-button")).click();

        //By XPath
//        Assert.assertEquals(webDriver.findElement(By.xpath("//h3[@data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
        //By cssSelector
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is incorrect");
    }

    @Test(dataProvider = "login-credentials")
    public void testInvalidUserLogin(String username,String password,String expectedMessage){
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();

        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        //By cssSelector
        Assert.assertEquals(errorMessage, expectedMessage,"Error message is incorrect");
    }

    @DataProvider(name="login-credentials")
    public Object[][] userCredentials() {
        return new Object[][]{
                {"","", "Epic sadface: Username is required"},
                {"","secret_sauce", "Epic sadface: Username is required"},
                {"standard_user","", "Epic sadface: Password is required"},
                {"standard_user", "invalid","Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSourceLab.class)
    public void testInvalidUserLoginDDTFromClass(String username,String password,String expectedMessage){
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();

        String errorMessage=webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        //By cssSelector
        Assert.assertEquals(errorMessage, expectedMessage,"Error message is incorrect");
    }
}
