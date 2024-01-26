package springboottesting.config;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class PostgreSQLContainerTestExecutionListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) {
        TestContainersConfig.postgres.start();
    }

    @Override
    public void afterTestClass(TestContext testContext) {
        TestContainersConfig.postgres.stop();
    }
}
