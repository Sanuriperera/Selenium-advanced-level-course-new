package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceCheckoutPage {
    private final WebDriver webDriver;
    By byFirstname = By.id("first-name");
    By byLastName= By.id("last-name");
    By byPostalCode=By.id("postal-code");
    By byContinue = By.id("continue");
    By byError =By.cssSelector("h3[data-test='error']");
    By byCancelBtn = By.id("cancel");

    public SauceCheckoutPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public SauceCheckoutPage typeFirstName(String fname) {
        webDriver.findElement(byFirstname).sendKeys(fname);
        return this;
    }

    public SauceCheckoutPage typeLastName(String lname) {
        webDriver.findElement(byLastName).sendKeys(lname);
        return this;
    }

    public SauceCheckoutPage typePostalCode(String postalCode){
        webDriver.findElement(byPostalCode).sendKeys(postalCode);
        return this;
    }

    public void clickContinue() {
        webDriver.findElement(byContinue).click();
    }


    public String getError() {
        return webDriver.findElement(byError).getText();
    }

    public void clickCancel() {
        webDriver.findElement(byCancelBtn).click();
    }
}
