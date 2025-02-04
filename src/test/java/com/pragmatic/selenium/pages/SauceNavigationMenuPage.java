package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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


    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(byLogoutLink));
        webDriver.findElement(byLogoutLink).click();
    }


    public void clickAllItemsLink() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(byAllItemsLink));
        webDriver.findElement(byAllItemsLink).click();
    }


    public void clickAbout() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(byAboutLink));
        webDriver.findElement(byAboutLink).click();
    }
}
