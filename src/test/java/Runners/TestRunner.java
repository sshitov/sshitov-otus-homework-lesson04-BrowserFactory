package Runners;

import Listeners.InfluxDBResultSender;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class TestRunner extends BlockJUnit4ClassRunner {

    private final InfluxDBResultSender listener;


    public TestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        this.listener = new InfluxDBResultSender();
    }

    @Override
    public void run(RunNotifier runNotifier) {
        runNotifier.addListener(listener);
        runNotifier.fireTestRunStarted(Description.TEST_MECHANISM);
        super.run(runNotifier);

    }
}
