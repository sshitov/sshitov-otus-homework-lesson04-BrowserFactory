package Tests.Helpers.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.LoadPolicy;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({ "classpath:Environment/${envType}/CommonConfig.properties",
        "classpath:Environment/Prd/CommonConfig.properties" })
public interface CommonConfig extends Config {

    @Key("site.baseUrl")
    String baseUrl();
}
