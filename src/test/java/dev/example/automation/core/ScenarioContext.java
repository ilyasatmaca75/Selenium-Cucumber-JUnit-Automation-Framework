package dev.example.automation.core;

import org.openqa.selenium.WebDriver;

/** PicoContainer creates a fresh context for every Cucumber scenario. */
public final class ScenarioContext {
    private WebDriver driver;
    public WebDriver driver() {
        if (driver == null) driver = BrowserFactory.create();
        return driver;
    }
    public WebDriver currentDriver() { return driver; } // does not accidentally launch a browser
    public void close() {
        if (driver != null) {
            try { driver.quit(); } finally { driver = null; }
        }
    }
}
