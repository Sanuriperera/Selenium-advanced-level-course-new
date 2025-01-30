package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SauceNavigationMenuPage {

    private final WebDriver webDriver;
    By byHamburgerMenu= By.id("react-burger-menu-btn");
    By byMenuWrap=By.cssSelector("[class='bm-menu-wrap']");
    By byLogoutLink=By.id("logout_sidebar_link");
    By byAllItemsLink=By.id("inventory_sidebar_link");
    By byAboutLink= By.id("about_sidebar_link");

    public SauceNavigationMenuPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void clickMenu() {
        webDriver.findElement(byHamburgerMenu).click();
    }

    public WebElement menuVisible(){
        return webDriver.findElement(byHamburgerMenu);
    }

    public WebElement getLogoutLink() {
        return webDriver.findElement(byLogoutLink);
    }

    public void clickLogout() {
        webDriver.findElement(byLogoutLink).click();
    }

    public WebElement getAllItemsLink(){
       return webDriver.findElement(byAllItemsLink);
    }

    public void clickAllItemsLink() {
        webDriver.findElement(byAllItemsLink).click();
    }

    public WebElement getAboutLink() {
        return webDriver.findElement(byAboutLink);
    }

    public void clickAbout() {
        webDriver.findElement(byAboutLink).click();
    }
}
