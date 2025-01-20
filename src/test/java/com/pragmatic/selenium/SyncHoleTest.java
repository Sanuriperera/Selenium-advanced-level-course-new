package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SyncHoleTest {
    private final String BASE_URL="https://eviltester.github.io/synchole/collapseable.html";
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);
    }
    @Test
    public void testClickingAboutLink(){
        webDriver.findElement(By.cssSelector("div#collapsable")).click();
        WebElement aboutLink=webDriver.findElement(By.id("aboutlink"));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink));
        aboutLink.click();
        Assert.assertEquals(webDriver.findElement(By.tagName("h1")).getText(),"About The Sync Hole");
    }

    @Test
    public void testClickingAboutLinkWithWaitForElementTobeVisible(){
        webDriver.findElement(By.cssSelector("div#collapsable")).click();
        WebElement aboutLink=webDriver.findElement(By.id("aboutlink"));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#collapsable")));
        aboutLink.click();
        Assert.assertEquals(webDriver.findElement(By.tagName("h1")).getText(),"About The Sync Hole");
    }

    @Test
    public void testClickingAboutLinkWithWaitForElementTobeClickableWithBy(){
        webDriver.findElement(By.cssSelector("div#collapsable")).click();
        WebElement aboutLink=webDriver.findElement(By.id("aboutlink"));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#collapsable")));
        aboutLink.click();
        Assert.assertEquals(webDriver.findElement(By.tagName("h1")).getText(),"About The Sync Hole");
    }
}
