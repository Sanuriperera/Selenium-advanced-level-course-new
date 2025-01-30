package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceProductListPage {
    private final WebDriver webDriver;
    By bySauceLabsBikeLightBtn= By.id("add-to-cart-sauce-labs-bike-light");
    By bySauceLabsBoltTShirtBtn=By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    By byCartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    By byProductList = By.cssSelector("[data-test='inventory-item']");
    By bySauceLabsBoltTShirtTitle=By.id("item_1_title_link");

    public SauceProductListPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public List<WebElement> getAllProducts() {
        return webDriver.findElements(byProductList);
    }

    public void clickAddToCartSauceLabsBikeLight() {
        webDriver.findElement(bySauceLabsBikeLightBtn).click();
    }

    public void clickAddToCartSauceLabsBoltTShirt(){
        webDriver.findElement(bySauceLabsBoltTShirtBtn).click();
    }

    public void clickCartIcon() {
        webDriver.findElement(byCartIcon).click();
    }

    public void clickSauceLabsBoltTShirtTitle() {
        webDriver.findElement(bySauceLabsBoltTShirtTitle).click();
    }

}
