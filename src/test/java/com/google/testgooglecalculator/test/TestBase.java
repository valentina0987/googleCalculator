package com.google.testgooglecalculator.test;

import com.google.testgooglecalculator.pages.MainGooglePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }



    @BeforeMethod(description = "Открыть главную страницу google")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.ru/");
        MainGooglePage mainGooglePage = new MainGooglePage(driver);
        mainGooglePage.getGoogleCalculatorPage();
    }


    @AfterMethod
    public void tearDown() {
        saveScreenshotPNG(driver);
        driver.quit();
    }
}
