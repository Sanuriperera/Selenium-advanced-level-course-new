package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SaProductListPage {
    @FindBy(css = "[data-test ='title']")
    WebElement ProductPageText;
    @FindBy(id="add-to-cart-sauce-labs-bike-light")
    WebElement SauceLabsBikeLightBtn;
    @FindBy(id="add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement SauceLabsBoltTShirtBtn;
    @FindBy(css = "[data-test='shopping-cart-link']")
    WebElement CartIcon;
    @FindBy(css = "[data-test='inventory-item']")
    List<WebElement> ProductList;
    @FindBy(id="item_1_title_link")
    WebElement SauceLabsBoltTShirtTitle;
    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory ']")
    WebElement AddToCartBtn;
    @FindBy(className = "shopping_cart_badge")
    WebElement CartBadge;
    @FindBy(css = "[data-test='remove-sauce-labs-bike-light']")
    WebElement SauceLabsBikeLightRemoveBtn;
    @FindBy(className = "inventory_item_name")
    List<WebElement> ItemName;
    @FindBy(className = "inventory_item_price")
    List<WebElement> ItemPrice;
    @FindBy(xpath = ".//div[@class= 'inventory_item_img']/a/img")
    List<WebElement> ItemImage;

    public SaProductListPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public String getProductText() {
        return ProductPageText.getText();
    }

    public List<WebElement> getAllProducts() {
        return ProductList;
    }

    public void clickAddToCartSauceLabsBikeLight() {
        SauceLabsBikeLightBtn.click();
    }

    public void clickAddToCartSauceLabsBoltTShirt(){
        SauceLabsBoltTShirtBtn.click();
    }

    public void clickCartIcon() {
        CartIcon.click();
    }

    public void clickSauceLabsBoltTShirtTitle() {
        SauceLabsBoltTShirtTitle.click();
    }

    public void clickAddToCart() {
        AddToCartBtn.click();
    }

    public String getCartBadgeCount() {
        return CartBadge.getText();
    }

    public String getRemoveBtnText() {
        return SauceLabsBikeLightRemoveBtn.getText();
    }

    public void clickRemoveButton() {
        SauceLabsBikeLightRemoveBtn.click();
    }

    public String getSauceLabsBikeLightAddToCartText() {
        return SauceLabsBikeLightBtn.getText();
    }

    public WebElement  getCartBadge() {
        try {
            return CartBadge;
        } catch (Exception e) {
            return null; // Return null if the badge does not exist
        }
    }

//    public List<String[]> getAllProductDetails() {
//
//        List<String[]> productDetails = new ArrayList<>();
//
//        for (int i = 0; i < productList.size(); i++) {
//            String name = itemNames.get(i).getText();
//            String price = itemPrices.get(i).getText();
//            String imageSrc = itemImages.get(i).getAttribute("src");
//
//            productDetails.add(new String[]{name, price, imageSrc});
//        }
//        return productDetails;
//    }
}
