package Tests.Helpers.Rules;

import Tests.Selenium.BaseTests;
import org.junit.rules.ExternalResource;

public class ExternalResourceClass extends ExternalResource {
    @Override
    protected void before() {
        BaseTests.webDriverManagerInitialize();
    }

    @Override
    protected void after() {
        BaseTests.closeWebDriver();
    }
}
