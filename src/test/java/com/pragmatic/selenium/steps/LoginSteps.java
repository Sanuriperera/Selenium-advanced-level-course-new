package com.pragmatic.selenium.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {
    private WebDriver webDriver;

    @Before
    public void before(){
        webDriver= new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @After
    public void after(){
        webDriver.quit();
    }

    @Given("user has accessed the login page")
    public void userHasAccessedTheLoginPage() {
        webDriver.get("https://www.saucedemo.com/");
    }

    @When("user provides valid credentials")
    public void userProvidesValidCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("the user should be directed to the product inventory page")
    public void theUserShouldBeDirectedToTheProductInventoryPage() {
        String actualTitle= webDriver.findElement(By.cssSelector("[data-test ='title']")).getText();
        Assert.assertEquals(actualTitle,"Products");
    }

    @When("user provides invalid password {string},{string}")
    public void userProvidesInvalidPassword(String username, String password) {
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("the user should be see error message {string}")
    public void theUserShouldBeSeeErrorMessage(String expectedMessage) {
        String errorMessage =webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        //By cssSelector
        Assert.assertEquals(errorMessage, expectedMessage,"Error message is incorrect");
    }
}
