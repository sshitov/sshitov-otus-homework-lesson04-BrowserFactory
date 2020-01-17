package Tests.Helpers.Managers;

import Tests.Helpers.Config.BrowserFirefoxConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {

    public static BrowserFirefoxConfig browserFirefoxConfig = ConfigFactory.create(BrowserFirefoxConfig.class, System.getProperties());

    protected static boolean setHeadless = browserFirefoxConfig.setHeadless();
    protected static int implicitlyWait = browserFirefoxConfig.implicitlyWait();
    protected static String driverPath = browserFirefoxConfig.driverPath();
    private GeckoDriverService service;

    @Override
    protected void startService() {
        if (null == service) {
            try {
                service = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(driverPath))
                        .usingAnyFreePort()
                        .build();
                service.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void stopService() {
        if (null != service && service.isRunning())
            service.stop();
    }

    @Override
    protected void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(setHeadless);
        driver = new FirefoxDriver(service, options);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
