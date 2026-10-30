package dev.example.automation.steps;

import dev.example.automation.core.ScenarioContext;
import dev.example.automation.core.UrlResolver;
import dev.example.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LoginSteps {
    private final ScenarioContext context;
    public LoginSteps(ScenarioContext context) { this.context = context; }
    private LoginPage loginPage() { return new LoginPage(context.driver()); }

    @Given("I open the demo login page")
    public void openDemoLoginPage() { loginPage().open(UrlResolver.appUrl()); }

    @When("I sign in with username {string} and password {string}")
    public void signIn(String username, String password) { loginPage().login(username, password); }

    @Then("the login message should be {string}")
    public void verifyMessage(String expected) { assertEquals(expected, loginPage().statusMessage()); }
}
