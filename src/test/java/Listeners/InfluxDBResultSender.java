package Listeners;

import Tests.Helpers.Config.InfluxDataBaseConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.util.concurrent.TimeUnit;

public class InfluxDBResultSender extends RunListener {
    // logger
    public static final Logger logger = LogManager.getLogger(InfluxDBResultSender.class.getName());

    // object influxDB configuration
    public static InfluxDataBaseConfig influxDataBaseConfig = ConfigFactory.create(InfluxDataBaseConfig.class, System.getProperties());

    // influxDB var
    private static final InfluxDB INFLUXDB = InfluxDBFactory.connect(influxDataBaseConfig.dbUrl(), influxDataBaseConfig.dbLogin(), influxDataBaseConfig.dbPassword());
    private static final String DATABASE = influxDataBaseConfig.dbName();

    // InfluxDB methods
    static{
        INFLUXDB.setDatabase(DATABASE);
    }

    public static void send(final Point point){
        INFLUXDB.write(point);

        // log result test that write to database
        logger.debug(point);
    }

        public static void sendTestMethodStatus(Description description, String status) {
        Point point = Point.measurement("testMethod")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("testClass", description.getClassName())
                .tag("description", description.getMethodName())
                .tag("result", status)
                .addField("TestsCount", description.testCount())
                .build();
        InfluxDBResultSender.send(point);
    }


    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        Point point = Point.measurement("testClass").time(System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                .tag("name", result.getClass().getMethods()[0].getClass().getName())
                .addField("duration", result.getRunTime())
                .build();
        InfluxDBResultSender.send(point);
    }

    public InfluxDBResultSender(){
        super();
    }

}