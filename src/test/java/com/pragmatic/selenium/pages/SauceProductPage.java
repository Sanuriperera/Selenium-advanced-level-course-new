package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceProductPage {
    private final WebDriver webDriver;
    By byName= By.cssSelector("[data-test='inventory-item-name']");
    By byDescription =By.cssSelector("[data-test='inventory-item-desc']");
    By byPrice=By.cssSelector("[data-test='inventory-item-price']");
    By byImage=By.cssSelector("[data-test='item-sauce-labs-bolt-t-shirt-img']");


    public SauceProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getName() {
        return webDriver.findElement(byName).getText();
    }

    public String getDescription() {
        return webDriver.findElement(byDescription).getText();
    }

    public String getPrice() {
        return webDriver.findElement(byPrice).getText();
    }

    public String getImage() {
        return webDriver.findElement(byImage).getAttribute("src");
    }
}
