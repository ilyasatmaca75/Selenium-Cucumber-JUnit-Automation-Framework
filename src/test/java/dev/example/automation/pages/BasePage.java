package dev.example.automation.pages;

import dev.example.automation.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final WebDriver driver;
    private final WebDriverWait wait;
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TestConfig.timeout());
    }
    protected WebElement visible(By selector) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
    protected WebElement clickable(By selector) {
        return wait.until(ExpectedConditions.elementToBeClickable(selector));
    }
    protected void type(By selector, String value) {
        WebElement element = visible(selector);
        element.clear();
        element.sendKeys(value);
    }
}
