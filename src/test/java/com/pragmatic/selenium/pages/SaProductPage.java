package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaProductPage {

    @FindBy(css = "[data-test='inventory-item-name']")
    WebElement name;
    @FindBy(css="[data-test='inventory-item-desc']")
    WebElement description;
    @FindBy(css = "[data-test='inventory-item-price']")
    WebElement price;
    @FindBy(css = "[data-test='item-sauce-labs-bolt-t-shirt-img']")
    WebElement image;

    public SaProductPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public String getName() {
        return name.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public String getImage() {
        return image.getAttribute("src");
    }
}
