package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SaNavigationMenuPage {

    @FindBy(id="react-burger-menu-btn")
    WebElement hamburgerMenu;

    @FindBy(css = "[class='bm-menu-wrap']")
    WebElement menuWrap;

    @FindBy(id="logout_sidebar_link")
    WebElement  logoutLink;

    @FindBy(id="inventory_sidebar_link")
    WebElement allItemsLink;

    @FindBy(id="about_sidebar_link")
    WebElement aboutLink;

    private WebDriver webDriver;

    public SaNavigationMenuPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public void clickMenu() {
        hamburgerMenu.click();
    }

    public WebElement menuVisible(){
        return hamburgerMenu;
    }

    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutLink.click();
    }

    public void clickAllItemsLink() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(allItemsLink));
        allItemsLink.click();
    }

    public void clickAbout() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink));
        aboutLink.click();
    }
}
