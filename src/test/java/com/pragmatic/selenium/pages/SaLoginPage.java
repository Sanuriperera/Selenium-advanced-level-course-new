package com.pragmatic.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaLoginPage {

    @FindBy(id="user-name")
    WebElement txtUserName;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(id="login-button")
    WebElement btnLogin;

    @FindBy(xpath = "//h3[@data-test ='error']")
    WebElement errMessage;

    private WebDriver webDriver;

    public SaLoginPage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public SaLoginPage typeUserName(String username) {
        txtUserName.sendKeys(username);
        return this;
    }

    public SaLoginPage typePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public String getError() {
        return errMessage.getText();
    }
}
