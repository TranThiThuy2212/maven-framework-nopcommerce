package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_Apply_BasePage_III extends BasePage{
    WebDriver driver;

    String projectPath=System.getProperty("user.dir");
    String emailAddress = "abc"+ganarateNumber()+"@gmail.com";


    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDrivers\\geckodriver.exe");
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data(){
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//a[@class='ico-register']");


        waitForElementClickable(driver,"//button[@id='register-button']");

        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//span[@id='FirstName-error']"),"First name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='LastName-error']"),"Last name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"),"Email is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Password-error']"),"Password is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='ConfirmPassword-error']"),"Password is required.");

    }

    @Test
    public void TC_02_Register_Invalid_Email(){
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//a[@class='ico-register']");

        sendkeyToElement(driver,"//input[@id='FirstName']","Tran");
        sendkeyToElement(driver,"//input[@id='LastName']","Thuy");
        sendkeyToElement(driver,"//input[@id='Email']","abc123@123$$");
        sendkeyToElement(driver,"//input[@id='Password']","123456");
        sendkeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"),"Wrong email");

    }
    @Test
    public void TC_03_Register_Success(){
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//a[@class='ico-register']");

        sendkeyToElement(driver,"//input[@id='FirstName']","Tran");
        sendkeyToElement(driver,"//input[@id='LastName']","Thuy");
        sendkeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendkeyToElement(driver,"//input[@id='Password']","123456");
        sendkeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//div[@class='result']"),"Your registration completed");

        waitForElementClickable(driver,"//a[text()='Continue']");
        clickToElement(driver,"//a[text()='Continue']");

    }
    @Test
    public void TC_04_Register_Exiting_Email(){
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//a[@class='ico-register']");

        sendkeyToElement(driver,"//input[@id='FirstName']","Tran");
        sendkeyToElement(driver,"//input[@id='LastName']","Thuy");
        sendkeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendkeyToElement(driver,"//input[@id='Password']","123456");
        sendkeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//div[@class='page-body']//li"),"The specified email already exists");

    }
    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars(){
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//a[@class='ico-register']");


        sendkeyToElement(driver,"//input[@id='FirstName']","Tran");
        sendkeyToElement(driver,"//input[@id='LastName']","Thuy");
        sendkeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendkeyToElement(driver,"//input[@id='Password']","1234");
        sendkeyToElement(driver,"//input[@id='ConfirmPassword']","1234");

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//span[@id='Password-error']"),"Password must meet the following rules:\nmust have at least 6 characters");

    }
    @Test
    public void TC_06_Register_Invalid_Confirm_Password(){
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//a[@class='ico-register']");

        sendkeyToElement(driver,"//input[@id='FirstName']","Tran");
        sendkeyToElement(driver,"//input[@id='LastName']","Thuy");
        sendkeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendkeyToElement(driver,"//input[@id='Password']","123456");
        sendkeyToElement(driver,"//input[@id='ConfirmPassword']","123444");

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//span[@id='ConfirmPassword-error']"),"The password and confirmation password do not match.");

    }

    public int ganarateNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
