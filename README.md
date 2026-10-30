# Modern Java UI automation framework — Selenium + Cucumber + JUnit Platform

A **generic**, scenario-isolated BDD starter rebuilt from an older Java/Cucumber/JUnit project. This is a clean replacement, **not** a copy of the original's application-specific steps.

## Requirements
- JDK **17+**, Maven **3.9+**, and Chrome, Edge, or Firefox installed.
- Selenium Manager automatically resolves browser drivers; on restricted networks, configure driver provisioning yourself.

## Run
```bash
mvn clean test
mvn clean test -Dheadless=false -Dbrowser=chrome
mvn clean test -Psmoke
mvn clean test -Dtags='@ui and not @wip'
mvn clean test -Dbase.url=https://your-test-environment.example/login
```
The default `classpath:demo/login.html` is a local HTML fixture and needs no remote server. The page and steps are illustrative only; replace both when integrating your AUT. `-Dbase.url` changes the target address, **not** the sample locators or expectations.

Configuration precedence: Java system property > environment variable (uppercase with dots replaced by underscores) > default. Supported keys: `browser` (chrome/firefox/edge), `headless` (true/false), `timeout.seconds` (default 10), `base.url`.

Results: `target/reports/cucumber.html`, `.json`, and `.xml`; screenshot attachments are produced on failing scenarios where a browser session exists. Maven fails on failing scenarios (no ignored failures).

## Architecture
```text
features/*.feature                 business-readable scenarios
     | Cucumber JUnit Platform engine
     v
steps/LoginSteps.java             thin Gherkin bindings + assertions
     | PicoContainer constructor injection (per scenario)
     v
core/ScenarioContext.java        lazy, scenario-owned browser lifecycle
     |                                 v                             hooks/ScenarioHooks.java
pages/LoginPage.java             failure screenshot + guaranteed quit
     |
pages/BasePage.java              explicit Selenium waits and interactions
     |
core/BrowserFactory.java         Selenium Manager, Chrome/Firefox/Edge
```

## Extend
1. Add a new `.feature` under `src/test/resources/features`.
2. Add a page object in `pages/` with private locators, behavior-oriented methods, and explicit waits.
3. Add glue in `steps/` receiving `ScenarioContext` via constructor (no global driver state).
4. Keep assertions in steps or dedicated assertion helpers, not in page objects.
5. Replace demo fixture with your app URL, and inject secrets via environment/CI secret store; never commit credentials.

## Parallelism
Parallel execution is intentionally **off by default**. Each scenario gets its own `ScenarioContext`, so tests are architecturally isolated. To enable parallel Cucumber execution, set `cucumber.execution.parallel.enabled=true` and `cucumber.execution.parallel.config.fixed.parallelism=N` in `junit-platform.properties`, then validate your target environment and CI capacity. Avoid static mutable test data.

## Design choices vs uploaded project
- JUnit 4 `@RunWith` runner -> **JUnit Platform suite**.
- Selenium 3 + legacy timeouts -> **Selenium 4 + `Duration` / explicit waits**.
- `InheritableThreadLocal` singleton -> **PicoContainer scenario injection**.
- Hard-coded Cydeo classes + public page elements -> **generic, encapsulated page objects**.
- External Google / web-table examples -> **deterministic local login fixture**.
- Old WebDriverManager dependency -> **Selenium Manager**.
- Failure swallowing -> **build fails on test failures**.
- HTML report + screenshot + JSON/XML outputs and CI workflow.

### Limitations
A real browser and dependencies are needed to execute the UI suite. The local fixture is not an authentication security test. This is a web UI starter, not a full API/mobile framework. For API testing, add a separate `api/` client layer and API-specific scenarios; keep the driver out of those scenarios.
