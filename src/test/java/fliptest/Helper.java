package fliptest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    public static void waitForElementClassUntilClickable(String element){
        WebDriverWait wait = new WebDriverWait(WebStepDefinitions.driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(element)));
    }

    public static void waitForElementXpathUntilClickable(String element){
        WebDriverWait wait = new WebDriverWait(WebStepDefinitions.driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
    }

    public static void waitForElementIdUntilClickable(String element){
        WebDriverWait wait = new WebDriverWait(WebStepDefinitions.driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
    }

    public static void waitForElementXpathUntilVisible(String element){
        WebDriverWait wait = new WebDriverWait(WebStepDefinitions.driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
    }

    public static void waitForElementClassUntilVisible(String element){
        WebDriverWait wait = new WebDriverWait(WebStepDefinitions.driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
    }
}
