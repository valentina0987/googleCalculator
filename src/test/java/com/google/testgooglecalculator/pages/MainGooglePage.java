package com.google.testgooglecalculator.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class MainGooglePage {
    WebDriver driver;

    private final By searchField = By.xpath("//input[@title='Поиск']");

    public MainGooglePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести в поле вода 'Калькулятор', нажать Enter")
    public void getGoogleCalculatorPage(){
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys("Калькулятор" + Keys.ENTER);
    }

}
