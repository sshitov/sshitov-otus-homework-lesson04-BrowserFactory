package Tests.Helpers.Managers;

import Tests.Helpers.Config.BrowserChromeConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager {

    public static BrowserChromeConfig browserChromeConfig = ConfigFactory.create(BrowserChromeConfig.class, System.getProperties());

    protected static boolean setHeadless = browserChromeConfig.setHeadless();
    protected static int implicitlyWait = browserChromeConfig.implicitlyWait();
    protected static String driverPath = browserChromeConfig.driverPath();
    private ChromeDriverService service;

    @Override
    public void startService() {
        if (null == service) {
            try {
                service = new ChromeDriverService.Builder()
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
    public void stopService() {
        if (null != service && service.isRunning())
            service.stop();
    }

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(setHeadless);
        driver = new ChromeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

}
