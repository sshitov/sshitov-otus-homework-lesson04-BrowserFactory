package Tests.Helpers.Rules;

import Listeners.InfluxDBResultSender;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class WatcherRule extends TestWatcher {
    @Override
    public void succeeded(Description description) {
        InfluxDBResultSender.sendTestMethodStatus(description, "PASS");

    }

    @Override
    protected void failed(Throwable e, Description description) {
        InfluxDBResultSender.sendTestMethodStatus(description, "FAILED");
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        InfluxDBResultSender.sendTestMethodStatus(description, "SKIPPED");
    }

}
