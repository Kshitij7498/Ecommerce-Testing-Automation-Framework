
package base;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
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
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--window-size=1440,1000");

        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }

        EdgeDriverService service = new EdgeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\WebDriver\\msedgedriver.exe"))
                .build();

        driver = new EdgeDriver(service, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
