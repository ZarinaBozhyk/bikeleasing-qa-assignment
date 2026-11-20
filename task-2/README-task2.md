# Task 2 – Gradle, Kotlin and Serenity BDD

A proof of concept with UI tests for www.bikeleasing.de and an API test for catfact.ninja.
It is written in Kotlin with JUnit Jupiter and Serenity BDD.
Every test run generates a Serenity report.

## Requirements and where they are covered

| Requirement | Implementation |
| --- | --- |
| Open www.bikeleasing.de and verify the URL | `HomePageTest` › *opens the Bikeleasing homepage at the correct URL*: checks that the browser ends up on `https://bikeleasing.de` |
| Automate further on this page | `HomePageTest` › *shows the expected navigation links*: parameterized test for the "Arbeitgeber" and "Selbstständige" menus |
| Retrieve 5 cat facts and verify the stated length of the first fact | `CatFactTest`: status 200, 5 facts, stated `length` of the first fact equals the length of its text |
| Use only Gradle, Kotlin, JUnit Jupiter and Serenity BDD | [build.gradle.kts](build.gradle.kts); see the note on Jackson under [Design decisions](#design-decisions) |
| Generate a Serenity report, use Serenity Steps | `HomePageSteps` (`@Step`) and Screenplay checks for the API; report in `target/site/serenity/index.html` |
| Keep it simple; Page Object or Screenplay | UI: Page Object + Serenity Steps; API: Screenplay |

## Prerequisites

- JDK 17. Gradle uses a toolchain and can download a matching JDK automatically.
- Google Chrome. Selenium downloads the matching driver automatically.

Gradle does not need to be installed; the project includes the Gradle wrapper.

## How to run

```bash
cd task-2

# macOS / Linux
./gradlew clean test

# Windows (PowerShell)
.\gradlew clean test
```

The Serenity report is generated after every run, also when tests fail:

```
task-2/target/site/serenity/index.html
```

Useful options:

```bash
# Show the browser window (PowerShell needs the quotes)
./gradlew clean test "-Dheadless.mode=false"

# Run one test class
./gradlew clean test --tests "api.tests.CatFactTest"
```

## Configuration

All run settings are in [src/test/resources/serenity.conf](src/test/resources/serenity.conf).
Each value can be overridden with a `-D` option on the command line.

| Setting | Value | Purpose |
| --- | --- | --- |
| `headless.mode` | `true` | Run Chrome without a window (CI-ready) |
| `webdriver.driver` | `chrome` | Browser for all UI tests |
| `webdriver.base.url` | `https://www.bikeleasing.de` | Page opened by `HomePage.open()` |
| `--window-size` | `1920,1080` | Desktop layout, so the desktop navigation is visible |
| `api.base.url` | `https://catfact.ninja` | Base URL of the API test |

## Project structure

```
task-2/
├── build.gradle.kts                 Dependencies, test task, Serenity report
├── settings.gradle.kts
└── src/test/
    ├── resources/
    │   └── serenity.conf            Browser, headless mode, base URLs
    └── kotlin/
        ├── config/
        │   └── TestConfig.kt        Reads settings from serenity.conf
        ├── ui/
        │   ├── pages/HomePage.kt    Page Object: locators, actions, page data
        │   ├── steps/HomePageSteps.kt  Serenity Steps: actions and checks for the report
        │   └── tests/HomePageTest.kt   UI tests
        └── api/
            ├── dto/                 Response model (Kotlin data classes)
            ├── tasks/FetchCatFactsTask.kt  Screenplay task: GET /facts
            └── tests/CatFactTest.kt  API test
```

## Design decisions

- **Page Object for the UI, Screenplay for the API.** I know Page Object from Java projects, so I used it for the UI.
  I used the recommended Screenplay pattern for the smaller API part to learn it.
- **URL check by origin.** The test compares `https://bikeleasing.de` (scheme and host), not the full URL,
  so a trailing slash or query parameters do not break it.

## Approach and notes

- Kotlin classes are `final` by default; Serenity needs `open` step classes to record steps.
- The cookie banner covered page elements; the menu tests close it with "continue without consent".
- The desktop menu is not visible in a small headless window; the window size is fixed in `serenity.conf`.
- IntelliJ reported a critical CVE in `jackson-databind`; fixed by updating the Jackson BOM to 2.21.8.

## Known issues

- IntelliJ reports two transitive vulnerabilities in Apache Commons Codec (CVE-2026-119078, CVE-2026-124077),
  pulled in by Serenity. They affect the Blake3 and GitIdentifiers classes, which this project does not use.
  Both were published in November 2026 and are still awaiting analysis, so no change was made.
- The console may show `Unable to find an exact match for CDP version`.
  The installed Chrome is newer than the Selenium DevTools bindings; this does not affect the tests.
- The UI tests check live website content. If the menu texts change, the menu tests fail on purpose.

## Next steps

- Move the UI tests to the Screenplay pattern as well.
- Run the tests in a CI pipeline (for example GitHub Actions) and publish the Serenity report.
- Extend the API checks: all facts, different `limit` values, response schema.
- Add automatic dependency scanning to CI.
