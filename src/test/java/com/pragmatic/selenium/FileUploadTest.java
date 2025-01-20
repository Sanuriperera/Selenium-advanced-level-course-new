package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest {

    @Test
    public void testFileUpload(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://the-internet.herokuapp.com/upload");

        //Get the absolute path of the image
        String filePath= new File("src/main/resources/images/SampleUploadImg.png").getAbsolutePath();
        webDriver.findElement(By.id("file-upload")).sendKeys(filePath);
        webDriver.findElement(By.id("file-submit")).click();

        Assert.assertEquals(webDriver.findElement(By.xpath("//h3")).getText(),"File Uploaded!");
        }

}
