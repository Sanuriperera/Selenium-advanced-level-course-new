package com.pragmatic.selenium.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/login.feature"}, //Path to feature files
        glue = {"com.pragmatic.selenium.steps"},//Package with step definitions
        monochrome = true, // Makes the console output readable

        plugin = {
                "pretty", //Prints Gherkin steps in the console
                "html:target/cucumber-reports.html", // HTML report
                "json:target/cucumber.json"//JSON report for ext integrations
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
