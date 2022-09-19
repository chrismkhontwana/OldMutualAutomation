package Engine;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class Base {
    public static WebDriver driver;
    public static ChromeOptions options;
     String selectedBrowser = "Chrome";
    @BeforeClass
    public static void setupWebdriverChromeDriver() {
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", "chromedriver");

    }


    @BeforeTest
    public void setup() {
        setupLocalDriver(selectedBrowser);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void setupLocalDriver(String selectedBrowser) {

        if ("Firefox".equals(selectedBrowser))
        {
            driver = new FirefoxDriver();
        }
        else if ("Chrome".equals(selectedBrowser))
        {
            options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            options.addArguments("disable-infobars");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); //remove 'Chrome is being controlled by automated test software'
            options.addArguments("test-type");
            options.addArguments("enable-strict-powerful-feature-restrictions");
            options.addArguments("--disable-popup-bloacking");
            options.addArguments("--dns-prefetch-disable");
            options.addArguments("--safebrowsing-disable-auto-update");
            options.addArguments("disable-restore-session-state");
            options.addArguments("--start-maximized");
            options.setCapability(ChromeOptions.CAPABILITY, options);
            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

            driver = new ChromeDriver(options);
        }

    }
}