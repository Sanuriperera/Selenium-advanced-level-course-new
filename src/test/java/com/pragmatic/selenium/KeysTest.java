package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeysTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
    }

//    @AfterMethod
//    public void afterMethod(){
//        webDriver.quit();
//    }

    @Test
    public void keyUp() {

        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        WebElement textField=webDriver.findElement(By.id("user-name"));

        Actions actions=new Actions(webDriver);
                actions.keyDown(Keys.SHIFT)
                .sendKeys(textField,"P")
                .keyUp(Keys.SHIFT)
                .sendKeys("ragmatic")
                .perform();

        Assert.assertEquals(textField.getDomProperty("value"),"Pragmatic");
    }

    @Test
    public void keyUpSelenium() {

        webDriver.manage().window().maximize();
        webDriver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textField=webDriver.findElement(By.id("my-text-id"));

        Actions actions=new Actions(webDriver);
        actions.sendKeys(textField,"Selenium!")
                .sendKeys(Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(Keys.CONTROL)
//                .sendKeys("xvv")
                .sendKeys("xvvv")
                .keyUp(Keys.CONTROL)
                .perform();

//        Assert.assertEquals(textField.getDomProperty("value"),"SeleniumSelenium!");
        Assert.assertEquals(textField.getDomProperty("value"),"SeleniumSeleniumSelenium!");

    }
}
