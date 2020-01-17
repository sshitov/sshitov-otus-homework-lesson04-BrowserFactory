package Tests.Helpers.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:BrowserEdgeConfig.properties")
public interface BrowserEdgeConfig extends Config {
    @Key("setBrowser")
    String browser();

    @Key("driverPath")
    @DefaultValue("/Drivers/MicrosoftWebDriver.exe")
    String driverPath();

    @Key("implicitlyWait")
    int implicitlyWait();
}
