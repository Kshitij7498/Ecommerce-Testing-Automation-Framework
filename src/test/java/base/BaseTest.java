
package base;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    protected final String baseUrl =
            System.getProperty("baseUrl", "https://www.saucedemo.com/");

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "edge")
                .toLowerCase();

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--window-size=1440,1000");

                if (headless) {
                    chromeOptions.addArguments(
                            "--headless=new",
                            "--no-sandbox",
                            "--disable-dev-shm-usage"
                    );
                }

                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--window-size=1440,1000");

                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }

                EdgeDriverService service = new EdgeDriverService.Builder()
                        .usingDriverExecutable(
                                new File("C:\\WebDriver\\msedgedriver.exe")
                        )
                        .build();

                driver = new EdgeDriver(service, edgeOptions);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                                + ". Use chrome or edge."
                );
        }

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(2));

        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));

        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
