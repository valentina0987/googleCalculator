package com.google.testgooglecalculator.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalculatorGooglePage {
    WebDriver driver;

    private final By divisionButton = By.xpath("//div[@aria-label='деление']");
    private final By multiplicationButton = By.xpath("//div[@aria-label='умножение']");
    private final By subtractionButton = By.xpath("//div[@aria-label='вычитание']");
    private final By additionButton = By.xpath("//div[@aria-label='сложение']");
    private final By sineButton = By.xpath("//div[@aria-label='синус']");
    private final By formulaField = By.xpath("//span[@jsname='ubtiRe']");
    private final By inputField = By.xpath("//span[@id='cwos']");
    private final By equalButton = By.xpath("//div[@aria-label='равно']");


    public CalculatorGooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public By getFormulaField() {
        return formulaField;
    }

    public By getInputField() {
        return inputField;
    }

    public By getEqualButton() {
        return equalButton;
    }

    public By getSineButton() {
        return sineButton;
    }

    public By getCalculatorButton(String digit) {
        return By.xpath(String.format("//div[@role='button'][normalize-space()='%s']", digit));
    }

    @Step("Ввести формулу {formula} в калькулятор и нажать кнопку 'равно'")
    public void inputAndCalculationFormula(String formula) {
        String inputFormula = formula.replace("", " ").replaceAll("[\\s]{2,}", " ").trim();
        String[] symbols = inputFormula.split(" ");
        for (String symbol : symbols) {
            switch (symbol) {
                case "*":
                    driver.findElement(multiplicationButton).click();
                    break;
                case "+":
                    driver.findElement(additionButton).click();
                    break;
                case "-":
                    driver.findElement(subtractionButton).click();
                    break;
                case "/":
                    driver.findElement(divisionButton).click();
                    break;
                default:
                    driver.findElement(getCalculatorButton(symbol)).click();
                    break;
            }
        }
        driver.findElement(equalButton).click();
    }

    @Step("Получить текст из вебэлемента {selector}")
    public String getElementText(By selector) {
        return driver.findElement(selector).getText();
    }

    @Step("Нажать на вебэлемент {selector}")
    public void clickElement(By selector){
        driver.findElement(selector).click();
    }

}
