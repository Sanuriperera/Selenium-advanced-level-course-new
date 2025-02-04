package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SaOverviewPage {

    @FindBy(css = "[data-test='title']")
    WebElement pageTitleText;

    @FindBy(id="finish")
    WebElement finishBtn;

    @FindBy(css = "[data-test='inventory-item']")
    List<WebElement>  productElements;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productName;

    @FindBy(className = "inventory_item_price")
    List<WebElement> price;

    @FindBy(css = "[data-test='subtotal-label']")
    WebElement totalItemPriceElement;

    @FindBy(css = "[data-test='total-label']")
    WebElement totalPriceElement;

    @FindBy(css = "[data-test='tax-label']")
    WebElement taxText;

    public SaOverviewPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public String getTitleText(){
        return pageTitleText.getText();
    }

    public void clickFinish() {
        finishBtn.click();
    }

    public List<WebElement> getProducts() {
        return productElements;
    }

    public int getProductCount() {
        return getProducts().size();
    }

    public String getProductName(int index) {
        return productName.get(index).getText();
    }

    public String getProductPrice(int index) {
        return price.get(index).getText();
    }

    public String getActualItemTotalPrice() {
        return totalItemPriceElement.getText().split(": ")[1];
    }

    public String  getActualTotalPriceWithSign() {
        String actualTotalPriceWithSign= totalPriceElement.getText().split(": ")[1];
        // Remove the dollar sign ($) from actual total price
        return actualTotalPriceWithSign.replace("$", "");
    }

    public double getTaxInDouble() {
        //get tax in text
        String tax=taxText.getText().split(": ")[1];
        String taxInText= tax.replace("$", "");
        //convert the tax value to double
        return  Double.parseDouble(taxInText);
    }
}
