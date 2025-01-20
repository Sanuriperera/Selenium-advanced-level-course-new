package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SyncButtonTest {

    private final String BASE_URL="https://eviltester.github.io/synchole/buttons.html";
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);
    }

    @Test
    public void testEasyToSyncButton(){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.findElement(By.id("easy00")).click();
        clickButton(By.id("easy01"));
        clickButton(By.id("easy02"));
        clickButton(By.id("easy03"));
//        webDriver.findElement(By.id("easy02")).click();
//        webDriver.findElement(By.id("easy03")).click();
        Assert.assertEquals(webDriver.findElement(By.id("easybuttonmessage")).getText(),"All Buttons Clicked");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    private void clickButton(By by){
        webDriver.findElement(by).click();
    }

    @Test
    public void testHardToSyncButton(){
        WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));
        waitAndClick(By.id("button00"));
        waitAndClick(By.id("button01"));
        waitAndClick(By.id("button02"));
        waitAndClick(By.id("button03"));
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button00")));
//        webDriver.findElement(By.id("button00")).click();
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button01")));
//        clickButton(By.id("button01"));
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button02")));
//        clickButton(By.id("button02"));
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button03")));
//        clickButton(By.id("button03"));
//        Assert.assertEquals(webDriver.findElement(By.id("buttonmessage")).getText(),"All Buttons Clicked");

    }

    private void waitAndClick(By by){
        WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));
        wait.withMessage("Button is not available to click");
        wait.pollingEvery(Duration.ofMillis(100));
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(by),
                ExpectedConditions.elementToBeClickable(by)
        ));
        clickButton(by);
    }
}
