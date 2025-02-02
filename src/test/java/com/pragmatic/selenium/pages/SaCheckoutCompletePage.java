package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaCheckoutCompletePage {

    @FindBy(css = "[data-test='title']")
    WebElement pageText;

    public SaCheckoutCompletePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public String getPageText() {
        return pageText.getText();
    }
}
