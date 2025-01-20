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
}
