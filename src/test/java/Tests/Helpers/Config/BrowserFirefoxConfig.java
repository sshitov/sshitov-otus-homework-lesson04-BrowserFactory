package Tests.Helpers.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:BrowserFirefoxConfig.properties")
public interface BrowserFirefoxConfig extends Config {

    @Key("setBrowser")
    String browser();

    @Key("driverPath")
    @DefaultValue("/Drivers/geckodriver.exe")
    String driverPath();

    @Key("setHeadless")
    boolean setHeadless();

    @Key("implicitlyWait")
    int implicitlyWait();
}
