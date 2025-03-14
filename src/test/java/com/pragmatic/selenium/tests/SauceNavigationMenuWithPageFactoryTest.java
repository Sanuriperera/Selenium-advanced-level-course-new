package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SaNavigationMenuPage;
import com.pragmatic.selenium.pages.SauceNavigationMenuPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceNavigationMenuWithPageFactoryTest extends BaseClass{

    @Test(description = "Test Case 5.1: Verify clicking the Menu button opens the navigation menu.")
    public void testHamburgerMenu(){
        //Open the Hamburger menu
        SaNavigationMenuPage navigationMenuPage=new SaNavigationMenuPage(webDriver);
        navigationMenuPage.clickMenu();
        Assert.assertTrue(navigationMenuPage.menuVisible().isDisplayed(),
                "Menu option is not visible after clicking the hamburger menu.");
    }


    @Test(description = "Test Case 5.2: Verify the Logout option logs the user out and redirects to the login page.")
    public void testLogoutFunctionality(){
        //Open the Hamburger menu
        SaNavigationMenuPage navigationMenuPage=new SaNavigationMenuPage(webDriver);
        navigationMenuPage.clickMenu();
        //Select logout
        navigationMenuPage.clickLogout();
        // Assert user is redirected to login page after logout
        String expectedUrl="https://www.saucedemo.com/";
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith(expectedUrl),"Does not redirect to the correct URL");
    }

    @Test(description = "Test Case 5.3: Verify the All Items option in the menu navigates back to the product listing page.")
    public void testAllItemsNavigation(){
        SaNavigationMenuPage navigationMenuPage=new SaNavigationMenuPage(webDriver);
        navigationMenuPage.clickMenu();
        navigationMenuPage.clickAllItemsLink();
        String expectedUrl= "https://www.saucedemo.com/inventory.html";
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith(expectedUrl),
                "All Items does not redirect to the correct URL");
    }


    @Test(description = "Test Case 5.4: Verify the About option redirects to the company website.")
    public void testVerifyAboutLink(){
        SaNavigationMenuPage navigationMenuPage=new SaNavigationMenuPage(webDriver);
        navigationMenuPage.clickMenu();
        navigationMenuPage.clickAbout();
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith("https://saucelabs.com/"),
                "About link does not redirect to the correct URL");
    }
}
