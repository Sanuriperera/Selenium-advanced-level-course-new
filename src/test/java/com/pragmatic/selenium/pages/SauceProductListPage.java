package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SauceProductListPage {
    private final WebDriver webDriver;
    By byProductPageText=By.cssSelector("[data-test ='title']");
    By bySauceLabsBikeLightBtn= By.id("add-to-cart-sauce-labs-bike-light");
    By bySauceLabsBoltTShirtBtn=By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    By byCartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    By byProductList = By.cssSelector("[data-test='inventory-item']");
    By bySauceLabsBoltTShirtTitle=By.id("item_1_title_link");
    By byAddToCartBtn =By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']");
    By byCartBadge=By.className("shopping_cart_badge");
    By bySauceLabsBikeLightRemoveBtn = By.cssSelector("[data-test='remove-sauce-labs-bike-light']");
    By byItemName=By.className("inventory_item_name");
    By byItemPrice=By.className("inventory_item_price");
    By byItemImage=By.xpath(".//div[@class= 'inventory_item_img']/a/img");

    public SauceProductListPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public List<WebElement> getAllProducts() {
        return webDriver.findElements(byProductList);
    }

    public void clickAddToCartSauceLabsBikeLight() {
        webDriver.findElement(bySauceLabsBikeLightBtn).click();
    }

    public void clickAddToCartSauceLabsBoltTShirt(){
        webDriver.findElement(bySauceLabsBoltTShirtBtn).click();
    }

    public void clickCartIcon() {
        webDriver.findElement(byCartIcon).click();
    }

    public void clickSauceLabsBoltTShirtTitle() {
        webDriver.findElement(bySauceLabsBoltTShirtTitle).click();
    }

    public void clickAddToCart() {
        webDriver.findElement(byAddToCartBtn).click();
    }

    public String getCartBadgeCount() {
        return webDriver.findElement(byCartBadge).getText();
    }

    public String getRemoveBtnText() {
        return webDriver.findElement(bySauceLabsBikeLightRemoveBtn).getText();
    }
    public void clickRemoveButton() {
        webDriver.findElement(bySauceLabsBikeLightRemoveBtn).click();
    }

    public String getSauceLabsBikeLightAddToCartText() {
        return webDriver.findElement(bySauceLabsBikeLightBtn).getText();
    }

    public WebElement  getCartBadge() {
        try {
            return webDriver.findElement(byCartBadge);
        } catch (Exception e) {
            return null; // Return null if the badge does not exist
        }
    }

    public String  getProductText() {
        return webDriver.findElement(byProductPageText).getText();
    }

    public List<String[]> getAllProductDetails() {
        List<WebElement> productElements = getAllProducts();
        List<String[]> productDetails = new ArrayList<>();

        for (WebElement product : productElements) {
            String name = product.findElement(byItemName).getText();
            String price = product.findElement(byItemPrice).getText();
            String imageSrc = product.findElement(byItemImage).getAttribute("src");

            productDetails.add(new String[]{name, price, imageSrc});
        }
        return productDetails;
    }
}
