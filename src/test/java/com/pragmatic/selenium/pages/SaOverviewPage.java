package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaOverviewPage {

    @FindBy(css = "[data-test='title']")
    WebElement pageTitleText;

    @FindBy(id="finish")
    WebElement finishBtn;

    @FindBy(css = "[data-test='inventory-item']")
    WebElement  productElements;

    @FindBy(className = "inventory_item_name")
    WebElement productName;

    @FindBy(className = "inventory_item_price")
    WebElement price;

    @FindBy(css = "[data-test='subtotal-label']")
    WebElement totalItemPriceElement;

    @FindBy(css = "[data-test='total-label']")
    WebElement totalPriceElement;

    @FindBy(css = "[data-test='tax-label']")
    WebElement taxText;

    public SaOverviewPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public String getTitleText(){
        return pageTitleText.getText();
    }

    public void clickFinish() {
        finishBtn.click();
    }
}
