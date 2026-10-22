# E-commerce Testing Automation Framework

A portfolio project demonstrating manual test design and UI automation for the public SauceDemo sample shop. Built with Java, Selenium WebDriver, TestNG, Maven, Page Object Model, and GitHub Actions.

> **Important:** This repository is a starter implementation. Run the suite yourself and update the results section with the actual outcome before presenting it on your resume. Demo sites and browser environments can change.

## Project goals

- Validate key user journeys: login, product inventory/sorting, cart, and checkout.
- Cover positive and negative scenarios.
- Keep locators and page interactions separate from test assertions using Page Object Model.
- Maintain a manual test-case inventory and defect-report template.
- Run tests locally and in CI using headless Chrome.

## Tech stack

- Java 17
- Selenium WebDriver 4
- TestNG
- Maven
- Chrome / ChromeDriver via Selenium Manager
- GitHub Actions

## Prerequisites

1. Install JDK 17 and configure `JAVA_HOME`.
2. Install Maven 3.9+.
3. Install Google Chrome for local headed runs.
4. Ensure the machine can access `https://www.saucedemo.com/`.

Selenium Manager normally resolves a compatible browser driver automatically. On locked-down networks, install/configure ChromeDriver manually.

## Run locally

From the repository root:

```bash
mvn clean test
```

Run headless:

```bash
mvn clean test -Dheadless=true
```

Override the application URL if you use a compatible test environment:

```bash
mvn clean test -DbaseUrl=https://www.saucedemo.com/
```

TestNG and Maven Surefire output is written under `target/surefire-reports/`.

## Current automated coverage

- **Login:** valid login, invalid password, empty credentials.
- **Products:** inventory visibility, ascending name sort, add item to cart.
- **Cart:** selected item appears, remove item.
- **Checkout:** missing first name validation, successful order completion.

The exact pass/fail outcome depends on the live demo site and browser. Do not claim a test count or pass rate without running the suite and inspecting the reports.

## Manual testing artifacts

- `manual-test-cases/manual-test-cases.csv` — initial cases with expected results and fields for actual results/status.
- `bug-reports/BUG-REPORT-TEMPLATE.md` — defect template.
- `test-data/test-data-notes.md` — safe demo-data guidance.

Open the CSV in Excel, Google Sheets, or LibreOffice. Execute each case manually and fill in actual results and status.

## Framework structure

```text
.
├── .github/workflows/maven-tests.yml
├── bug-reports/
├── manual-test-cases/
├── src/test/java/
│   ├── base/BaseTest.java
│   ├── pages/
│   └── tests/
├── test-data/
├── pom.xml
└── testng.xml
```

## Design notes

- `BaseTest` creates and quits a browser for each test method.
- Page objects encapsulate page locators and actions.
- Test classes contain assertions and test intent.
- `testng.xml` defines the suite.
- GitHub Actions runs Maven tests in headless mode on pushes and pull requests.

## Known limitations / next improvements

- Add explicit waits for every asynchronous UI transition instead of relying on the small implicit wait.
- Add screenshot capture on failure and attach screenshots to CI artifacts.
- Add data-driven tests using TestNG `@DataProvider`.
- Add a test report such as Allure or ExtentReports.
- Add API checks with REST Assured or Postman if relevant to the target role.
- Add browser matrix testing once the suite is stable.
- Review selectors and re-run the suite if SauceDemo changes its UI.

## Results log

| Date | Environment | Command | Passed | Failed | Notes |
|---|---|---|---:|---:|---|
| Not run yet | Fill after execution | `mvn clean test` | — | — | Replace this row with real results |

## Resume description (use only after you run and verify it)

**E-commerce Testing Automation Framework | Java, Selenium WebDriver, TestNG, Maven**
- Created UI automation for login, inventory, cart, and checkout flows using Selenium WebDriver and TestNG.
- Structured browser interactions with the Page Object Model and managed dependencies/build execution with Maven.
- Prepared manual test cases for positive and negative scenarios and a reusable defect-report template.
- Configured a GitHub Actions workflow to execute the test suite in headless Chrome.

Adjust this description to match what you personally completed and verified.

## Ethical testing

This project targets a public demo application intended for practice. Do not run automation against production systems without explicit authorization.
