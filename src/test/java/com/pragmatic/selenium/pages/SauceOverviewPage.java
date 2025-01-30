package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceOverviewPage {
    private final WebDriver webDriver;
    By byPageTitleText= By.cssSelector("[data-test='title']");
    By byFinishBtn= By.id("finish");
    By byproductElements=By.cssSelector("[data-test='inventory-item']");
    By byProductName= By.className("inventory_item_name");
    By byPrice=By.className("inventory_item_price");

    public SauceOverviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getTitleText(){
        return webDriver.findElement(byPageTitleText).getText();
    }

    public void clickFinish() {
        webDriver.findElement(byFinishBtn).click();
    }

    public List<WebElement> getProducts() {
        return webDriver.findElements(byproductElements);
    }

    public String getProductName() {
        return webDriver.findElement(byProductName).getText();
    }

    public String getPrice() {
        return webDriver.findElement(byPrice).getText();
    }
}
