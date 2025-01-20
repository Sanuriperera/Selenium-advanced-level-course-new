package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DisableSavePasswordPopupTest {
    @Test
    public void testDisableBrowserPopup(){
        ChromeOptions options =new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        Map<String,Object> prefs= new HashMap<>();
        prefs.put("credentials_enable_service", false);  // Disable password saving feature
        prefs.put("profile.password_manager_enabled", false);  // Disable password manager

        // Set ChromeOptions
        options.setExperimentalOption("prefs", prefs);

        // Initialize WebDriver with the ChromeOptions
        WebDriver webDriver = new ChromeDriver(options);

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //compare
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test ='title']")).getText(),"Products");
    }
}
