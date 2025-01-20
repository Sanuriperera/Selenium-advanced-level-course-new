package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NavigationAndLogoutTest {

    WebDriver webDriver;

    @BeforeMethod
    public void openLoginPage(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
    }

//    @AfterMethod
//    public void closeBrowser(){
//        webDriver.quit();
//    }
    @Test
    public void testHamburgerMenu(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");

        //Open the Hamburger menu
        webDriver.findElement(By.id("react-burger-menu-btn")).click();
        Assert.assertTrue(webDriver.findElement(By.cssSelector("[class='bm-menu-wrap']")).isDisplayed(),
                "Menu option is not visible after clicking the hamburger menu.");
    }

    @Test
    public void testLogoutFunctionality(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        //Open the Hamburger menu
        webDriver.findElement(By.id("react-burger-menu-btn")).click();
        Assert.assertTrue(webDriver.findElement(By.cssSelector("[class='bm-menu-wrap']")).isDisplayed(),
                "Menu option is not visible after clicking the hamburger menu.");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));

        //Select logout
        webDriver.findElement(By.id("logout_sidebar_link")).click();

        // Assert user is redirected to login page after logout
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith("https://www.saucedemo.com/"));
    }

    @Test
    public void testAllItemsNavigation(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        //Open the Hamburger menu
        webDriver.findElement(By.id("react-burger-menu-btn")).click();
        Assert.assertTrue(webDriver.findElement(By.cssSelector("[class='bm-menu-wrap']")).isDisplayed(),
                "Menu option is not visible after clicking the hamburger menu.");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inventory_sidebar_link")));
        webDriver.findElement(By.id("inventory_sidebar_link")).click();
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"),
                "All Items does not redirect to the correct URL");
    }

    @Test
    public void testVerifyAboutLink(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
        //Open the Hamburger menu
        webDriver.findElement(By.id("react-burger-menu-btn")).click();
        Assert.assertTrue(webDriver.findElement(By.cssSelector("[class='bm-menu-wrap']")).isDisplayed(),
                "Menu option is not visible after clicking the hamburger menu.");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("about_sidebar_link")));
        webDriver.findElement(By.id("about_sidebar_link")).click();
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith("https://saucelabs.com/"),
                "About link does not redirect to the correct URL");
    }
}
