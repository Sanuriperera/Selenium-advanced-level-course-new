package com.pragmatic.selenium;

import org.testng.annotations.DataProvider;

public class DataProviderSourceLab {
    @DataProvider(name="login-credentials")
    public Object[][] userCredentials() {
        return new Object[][]{
                {"","", "Epic sadface: Username is required"},
                {"","secret_sauce", "Epic sadface: Username is required"},
                {"standard_user","", "Epic sadface: Password is required"},
                {"standard_user", "invalid","Epic sadface: Username and password do not match any user in this service"}
        };
    }


    @DataProvider(name="user-information")
    public Object[][] userDetails() {
        return new Object[][]{
                {"","","", "Error: First Name is required"},
                {"","perera","10400", "Error: First Name is required"},
                {"sanuri","", "10400","Error: Last Name is required"},
                {"sanuri","perera","", "Error: Postal Code is required"}
        };
    }

    @DataProvider(name="productData")
    public Object[][]  expectedProducts() {
        return new Object[][]{
                {"Sauce Labs Backpack", "$29.99", "sauce-backpack-1200x1500.0a0b85a3.jpg"},
                {"Sauce Labs Bike Light", "$9.99", "bike-light-1200x1500.37c843b0.jpg"},
                {"Sauce Labs Bolt T-Shirt", "$15.99", "bolt-shirt-1200x1500.c2599ac5.jpg"},
                {"Sauce Labs Fleece Jacket", "$49.99", "sauce-pullover-1200x1500.51d7ffaf.jpg"},
                {"Sauce Labs Onesie", "$7.99", "red-onesie-1200x1500.2ec615b2.jpg"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99", "red-tatt-1200x1500.30dadef4.jpg"}
        };
    };
}
