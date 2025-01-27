package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceLoginPage {

    private final WebDriver webDriver;
    By byUsername= By.id("user-name");
    By byPassword =By.id("password");
    By byLoginButton=By.id("login-button");
    By byError=By.cssSelector("[data-test ='title']");

    public SauceLoginPage(WebDriver webDriver){
        this.webDriver=webDriver;
    }
    public SauceLoginPage typeUserName(String username) {
        webDriver.findElement(byUsername).sendKeys(username);
        return this;
    }

    public SauceLoginPage typepasswaord(String password) {
        webDriver.findElement(byPassword).sendKeys(password);
        return this;
    }

    public void clickLogin() {
        webDriver.findElement(byLoginButton).click();
    }

    public String getError(){
        return webDriver.findElement(byError).getText();
    }

    public SauceLoginPage clearUserName(){
        webDriver.findElement(byUsername).clear();
        return this;
    }
}
