package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    // Get a new WebDriver Instance.
    // There are various implementations for this depending on browser. The required browser can be set as an environment variable.
    // Refer http://getgauge.io/documentation/user/current/managing_environments/README.html
    public static WebDriver getDriver() {

        String browser = System.getenv("browser");

        switch (browser)
        {
            case "ie":
            	WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case "firefox":
            	WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "chrome":
            	WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            default:
            	WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
}
