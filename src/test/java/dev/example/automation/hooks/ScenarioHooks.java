package dev.example.automation.hooks;

import dev.example.automation.core.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScenarioHooks {
    private final ScenarioContext context;
    public ScenarioHooks(ScenarioContext context) { this.context = context; }

    @After
    public void tearDown(Scenario scenario) {
        try {
            WebDriver driver = context.currentDriver();
            if (scenario.isFailed() && driver instanceof TakesScreenshot screenshotSource) {
                try {
                    scenario.attach(screenshotSource.getScreenshotAs(OutputType.BYTES),
                            "image/png", "Failure screenshot");
                } catch (RuntimeException screenshotFailure) {
                    scenario.log("Screenshot could not be captured: " + screenshotFailure.getMessage());
                }
            }
        } finally {
            context.close();
        }
    }
}
