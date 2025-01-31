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
    By byTotalItemPriceElement=By.cssSelector("[data-test='subtotal-label']");
    By byTotalPriceElement=By.cssSelector("[data-test='total-label']");
    By byTaxText=By.cssSelector("[data-test='tax-label']");

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

    public String getProductName(int index) {
        return getProducts().get(index).findElement(byProductName).getText();
    }

    public String getProductPrice(int index) {
        return getProducts().get(index).findElement(byPrice).getText();
    }

    public String getActualItemTotalPrice() {
        return webDriver.findElement(byTotalItemPriceElement).getText().split(": ")[1];
    }

    public String  getActualTotalPriceWithSign() {
        String actualTotalPriceWithSign= webDriver.findElement(byTotalPriceElement).getText().split(": ")[1];
        // Remove the dollar sign ($) from actual total price
        return actualTotalPriceWithSign.replace("$", "");
    }

    public String getTaxInText() {
        String tax=webDriver.findElement(byTaxText).getText().split(": ")[1];
        return tax.replace("$", "");
    }

    public int getProductCount() {
        return getProducts().size();
    }
}
