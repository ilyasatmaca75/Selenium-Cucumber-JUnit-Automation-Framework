package dev.example.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Replace locators and methods with those of your application. */
public final class LoginPage extends BasePage {
    private static final By USERNAME = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By SUBMIT = By.cssSelector("button[type='submit']");
    private static final By MESSAGE = By.id("message");

    public LoginPage(WebDriver driver) { super(driver); }
    public void open(String url) { driver.get(url); }
    public void login(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        clickable(SUBMIT).click();
    }
    public String statusMessage() { return visible(MESSAGE).getText(); }
}
