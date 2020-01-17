package Tests.Helpers.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:ProdEnvConfig.properties")
public interface ProdEnvConfig extends Config {

    @Key("site.baseUrl")
    String baseUrl();
}
