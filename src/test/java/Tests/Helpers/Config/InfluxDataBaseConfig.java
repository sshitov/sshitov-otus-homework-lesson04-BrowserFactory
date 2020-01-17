package Tests.Helpers.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:InfluxDateBaseConfig.properties")
public interface InfluxDataBaseConfig extends Config{

    @Key("database.name")
    String dbName();

    @Key("database.Url")
    String dbUrl();

    @Key("database.login")
    @DefaultValue("root")
    String dbLogin();

    @Key("database.password")
    @DefaultValue("root")
    String dbPassword();

}
