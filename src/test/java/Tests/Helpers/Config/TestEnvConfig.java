package Tests.Helpers.Config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:ProdEnvConfig.properties")
public interface TestEnvConfig extends Config {
    @Key("site.baseUrl")
    String baseUrl();
}
