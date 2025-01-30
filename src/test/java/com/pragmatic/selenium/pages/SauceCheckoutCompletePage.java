package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceCheckoutCompletePage {
    private final WebDriver webDriver;
    By byPageText= By.cssSelector("[data-test='title']");

    public SauceCheckoutCompletePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getPageText() {
        return webDriver.findElement(byPageText).getText();
    }
}
