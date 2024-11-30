package mainCode.Common;

import utils.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * This class contains the initialization and closure of the driver before and after each class execution.
 */
public class BaseTest {

    public WebDriver driver;

    /**
     * Initialize driver before each class begins based on config.properties file values.
     */
    @BeforeClass
    public void setUp() {
        String browser = ConfigManager.getProperty("browser");
        boolean isHeadless = Boolean.parseBoolean(ConfigManager.getProperty("headless"));

        if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                options.addArguments("-headless");
            }
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(options);

        } else {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        if (Boolean.parseBoolean(ConfigManager.getProperty("maximize")))
            driver.manage().window().maximize();
        driver.get(ConfigManager.getProperty("baseUrl"));
    }

    /**
     * Close driver after each class is completed.
     */
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}