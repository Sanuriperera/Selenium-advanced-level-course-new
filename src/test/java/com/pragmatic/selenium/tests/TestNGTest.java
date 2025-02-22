package com.pragmatic.selenium.tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGTest {
    @Test(groups = {"regression","smoke"})
    @Parameters({"browser","headless"})
    public void testMethod1(@Optional("chrome") String browser,@Optional("false") String headless){
        System.out.println("browser ="+browser +", headless"+headless);
        System.out.println("testMethod1");
    }
    @Test(groups = {"regression","smoke"})
    public void testMethod2(){
        System.out.println("testMethod2");
    }
}
