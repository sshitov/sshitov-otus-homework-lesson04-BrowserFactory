package Tests.Helpers.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:BrowserChromeConfig.properties")
public interface BrowserChromeConfig extends Config{

    @Key("setBrowser")
    @DefaultValue("CHROME")
    String browser();

    @Key("driverPath")
    @DefaultValue("/Drivers/chromedriver.exe")
    String driverPath();

    @Key("setHeadless")
    boolean setHeadless();

    @Key("implicitlyWait")
    int implicitlyWait();

}
