package Tests.Selenium;
import Runners.TestRunner;
import Tests.Helpers.Rules.ExternalResourceClass;
import Tests.Helpers.Rules.WatcherRule;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;


// @RunWith(TestRunner.class)
public class OtusTests extends BaseTests{

/*    //Rule for Watcher listener
    @Rule
    public WatcherRule watcher = new WatcherRule();*/

    @ClassRule
    public static ExternalResourceClass ActBeforeAndAfterClass = new ExternalResourceClass();

    @Before
    public void createWebDriver() {

        webDriverInitializeAndConfig();
    }

    @Test
    public void openMainPage() {
        // test arrange and act
        String expectTitle = "Авторские онлайн‑курсы для профессионалов";

        openPage(baseUrl);
        String factTitle = findPageElementToVerify(By.tagName("h1"));

        // test assert
        Assert.assertEquals("Main page title not meet to Expected",factTitle, expectTitle);
    }

}