package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceCartPage {

    private final WebDriver webDriver;
     By bySauceLabsBikeLightTitle= By.id("item_0_title_link");
     By bySauceLabsBikeLightDescription =By.cssSelector("[data-test='inventory-item-desc']");
     By byCartItemListLocator=By.cssSelector("[data-test='inventory-item-name']");
     By byInitialCartCountText =By.cssSelector("[data-test='shopping-cart-badge']");
     By byBikeLightRemovalButton =By.id("remove-sauce-labs-bike-light");
     By byContinueShoppingButton=By.id("continue-shopping");
     By byCheckoutBtn=By.id("checkout");

    public SauceCartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getSauceLabsBikeLightTitle(){
        return webDriver.findElement(bySauceLabsBikeLightTitle).getText();
    }

    public String getSauceLabsBikeLightDescription(){
        return webDriver.findElement(bySauceLabsBikeLightDescription).getText();
    }

    public List<WebElement> getCartItems(){
        return webDriver.findElements(byCartItemListLocator);
    }

    public String getInitialCartCountText(){
        return webDriver.findElement(byInitialCartCountText).getText();
    }

    public void clickBikeLightRemovalButton(){
        webDriver.findElement(byBikeLightRemovalButton).click();
    }

    public void clickContinueShoppingButton(){
        webDriver.findElement(byContinueShoppingButton).click();
    }

    public void clickCheckoutBtn(){
        webDriver.findElement(byCheckoutBtn).click();
    }
}
