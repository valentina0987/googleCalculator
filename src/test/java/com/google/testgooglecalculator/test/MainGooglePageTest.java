package com.google.testgooglecalculator.test;

import com.google.testgooglecalculator.pages.CalculatorGooglePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MainGooglePageTest extends TestBase{

    @Test(description = "Проверка правильности расчета формулы")
    public void calculationShouldEqualTo1() {
        final String FORMULA = "(1 + 2) * 3 - 40 / 5";

        CalculatorGooglePage calculator = new CalculatorGooglePage(driver);
        calculator.inputAndCalculationFormula(FORMULA);

        String expectedResult = FORMULA.replace('/', '\u00f7').replace('*', '\u00d7') + " =";
        Assert.assertEquals(calculator.getElementText(calculator.getFormulaField()), expectedResult, "Формула не соответствует введенной");
        Assert.assertEquals(calculator.getElementText(calculator.getInputField()), "1", "Результат расчета не соответствует ожидаемому");
    }

    @Test(description = "Проверка появления сообщения 'Infinity' при делении на ноль")
    public void calculationShouldEqualToInfinity() {
        final String FORMULA = "6 / 0";

        CalculatorGooglePage calculator = new CalculatorGooglePage(driver);
        calculator.inputAndCalculationFormula(FORMULA);

        String expectedResult = FORMULA.replace('/', '\u00f7') + " =";
        Assert.assertEquals(calculator.getElementText(calculator.getFormulaField()), expectedResult, "Формула не соответствует введенной");
        Assert.assertEquals(calculator.getElementText(calculator.getInputField()), "Infinity", "Отсутствует сообщение Infinity");
    }

    @Test(description = "Проверка появления сообщения 'Error' при попытке расчетать синус без значения")
    public void calculationShouldEqualToError() {
        CalculatorGooglePage calculator = new CalculatorGooglePage(driver);
        calculator.clickElement(calculator.getSineButton());
        calculator.clickElement(calculator.getEqualButton());

        Assert.assertEquals(calculator.getElementText(calculator.getFormulaField()), "sin() =", "Формула не соответствует введенной");
        Assert.assertEquals(calculator.getElementText(calculator.getInputField()), "Error", "Отсутствует сообщение Error");
    }

}
