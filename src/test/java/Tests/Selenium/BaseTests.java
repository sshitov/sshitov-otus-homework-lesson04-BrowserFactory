package Tests.Selenium;

import Tests.Helpers.Config.*;
import Tests.Helpers.Enums.DriverType;
import Tests.Helpers.Managers.DriverManager;
import Tests.Helpers.Managers.DriverManagerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTests {

    // initialize properties
    public static ProdEnvConfig prodEnvConfig = ConfigFactory.create(ProdEnvConfig.class, System.getProperties());

    // initialize logger
    public static final Logger logger = LogManager.getLogger(BaseTests.class.getName());

    // common test variables
    protected static WebDriver driver;
    protected static DriverManager driverManager;
    protected WebDriverWait wait;
    protected static String baseUrl = prodEnvConfig.baseUrl();
    protected static String browser = System.getProperty("browser").toUpperCase();

    // test methods
    public static void webDriverManagerInitialize(){
        logger.debug("setup WebDriver");
        WebDriverManager.chromedriver().setup();
    }

    public void webDriverInitializeAndConfig(){
        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browser));
        driver = driverManager.getDriver();
        logger.debug("Initialize webDriver and set headless");
        wait = new WebDriverWait(driver, 10);

    }

    public static void closeWebDriver(){
        driverManager.quitDriver();
    }

    public void openPage(String url){
        driver.get(url);
    }

    public String findPageElementToVerify(Object locator){
        return driver.findElement((By) locator).getText();
    }

    public WebElement findPageElementAndAct(Object locator){
        return driver.findElement((By) locator);
    }

}
