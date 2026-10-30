package dev.example.automation.core;

import dev.example.automation.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class BrowserFactory {
    private BrowserFactory() { }
    public static WebDriver create() {
        boolean headless = TestConfig.headless();
        // Selenium Manager manages matching browser drivers. No hard-coded executables.
        return switch (TestConfig.browser()) {
            case "chrome" -> {
                ChromeOptions opts = new ChromeOptions();
                if (headless) opts.addArguments("--headless=new");
                opts.addArguments("--window-size=1440,900");
                yield new ChromeDriver(opts);
            }
            case "firefox" -> {
                FirefoxOptions opts = new FirefoxOptions();
                if (headless) opts.addArguments("-headless");
                yield new FirefoxDriver(opts);
            }
            case "edge" -> {
                EdgeOptions opts = new EdgeOptions();
                if (headless) opts.addArguments("--headless=new");
                yield new EdgeDriver(opts);
            }
            default -> throw new IllegalArgumentException("Unknown browser: " + TestConfig.browser());
        };
    }
}
