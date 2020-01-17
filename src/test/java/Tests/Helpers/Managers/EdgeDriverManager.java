package Tests.Helpers.Managers;

import Tests.Helpers.Config.BrowserEdgeConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class EdgeDriverManager extends DriverManager{

    public static BrowserEdgeConfig browserEdgeConfig = ConfigFactory.create(BrowserEdgeConfig.class, System.getProperties());

    protected static int implicitlyWait = browserEdgeConfig.implicitlyWait();
    protected static String driverPath = browserEdgeConfig.driverPath();
    private EdgeDriverService service;

    @Override
    protected void startService() {
        if (null == service) {
            try {
                service = new EdgeDriverService.Builder()
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
        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
