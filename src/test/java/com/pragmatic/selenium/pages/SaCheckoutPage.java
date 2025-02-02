package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaCheckoutPage {

    @FindBy(id ="first-name")
    WebElement  firstname;

    @FindBy(id ="last-name")
    WebElement lastName;

    @FindBy(id="postal-code")
    WebElement postalCodeEle;

    @FindBy(id="continue")
    WebElement continueBtn;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    @FindBy(id="cancel")
    WebElement cancelBtn;

    public SaCheckoutPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public SaCheckoutPage typeFirstName(String fname) {
        firstname.sendKeys(fname);
        return this;
    }

    public SaCheckoutPage typeLastName(String lname) {
        lastName.sendKeys(lname);
        return this;
    }

    public SaCheckoutPage typePostalCode(String postalCode){
        postalCodeEle.sendKeys(postalCode);
        return this;
    }

    public void clickContinue() {
        continueBtn.click();
    }

    public String getError() {
        return errorMessage.getText();
    }

    public void clickCancel() {
        cancelBtn.click();
    }
}
