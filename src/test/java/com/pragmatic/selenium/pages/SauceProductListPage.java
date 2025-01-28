package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceProductListPage {
    private final WebDriver webDriver;
    By byProductList = By.cssSelector("[data-test='inventory-item']");

    public SauceProductListPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public List<WebElement> getAllProducts() {
        return webDriver.findElements(byProductList);
    }
}
