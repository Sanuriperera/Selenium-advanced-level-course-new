package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SaCartPage {

    @FindBy(id="item_0_title_link")
    WebElement sauceLabsBikeLightTitle;

    @FindBy(css = "[data-test='inventory-item-desc']")
    WebElement  sauceLabsBikeLightDescription;

    @FindBy(css = "[data-test='inventory-item-name']")
    List<WebElement>  cartItemListLocator;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    WebElement initialCartCountText;

    @FindBy(id = "remove-sauce-labs-bike-light")
    WebElement bikeLightRemovalButton;

    @FindBy(id="continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(id="checkout")
    WebElement checkoutBtn;

    public SaCartPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public String getSauceLabsBikeLightTitle(){
        return sauceLabsBikeLightTitle.getText();
    }

    public String getSauceLabsBikeLightDescription(){
        return sauceLabsBikeLightDescription.getText();
    }

    public List<WebElement> getCartItems(){
        return cartItemListLocator;
    }

    public String getInitialCartCountText(){
        return initialCartCountText.getText();
    }

    public void clickBikeLightRemovalButton(){
        bikeLightRemovalButton.click();
    }

    public void clickContinueShoppingButton(){
        continueShoppingButton.click();
    }

    public void clickCheckoutBtn(){
        checkoutBtn.click();
    }
}
