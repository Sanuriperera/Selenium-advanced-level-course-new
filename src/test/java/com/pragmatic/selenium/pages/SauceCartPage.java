package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceCartPage {

    private final WebDriver webDriver;

     By bySauceLabsBikeLightBtn= By.id("add-to-cart-sauce-labs-bike-light");
     By byCartIcon = By.cssSelector("[data-test='shopping-cart-link']");
     By bySauceLabsBikeLightTitle= By.id("item_0_title_link");
     By bySauceLabsBikeLightDescription =By.cssSelector("[data-test='inventory-item-desc']");
     By bySauceLabsBoltTShirtBtn=By.id("add-to-cart-sauce-labs-bolt-t-shirt");
     By byCartItemListLocator=By.cssSelector("[data-test='inventory-item-name']");
     By byInitialCartCountText =By.cssSelector("[data-test='shopping-cart-badge']");
     By byBikeLightRemovalButton =By.id("remove-sauce-labs-bike-light");
     By byContinueShoppingButton=By.id("continue-shopping");
     By byCheckoutBtn=By.id("checkout");

    public SauceCartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickAddToCartSauceLabsBikeLight() {
        webDriver.findElement(bySauceLabsBikeLightBtn).click();
    }

    public void clickCartIcon() {
        webDriver.findElement(byCartIcon).click();
    }

    public String getSauceLabsBikeLightTitle(){
        return webDriver.findElement(bySauceLabsBikeLightTitle).getText();
    }

    public String getSauceLabsBikeLightDescription(){
        return webDriver.findElement(bySauceLabsBikeLightDescription).getText();
    }

    public void clickAddToCartSauceLabsBoltTShirt(){
        webDriver.findElement(bySauceLabsBoltTShirtBtn).click();
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
