package util;

import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ThrowingRunnable;

import java.util.concurrent.TimeUnit;

public class TestExecutor {
    private final String description;
    private final ThrowingRunnable assertion;
    private final int repetitionCount;

    public TestExecutor(String description, ThrowingRunnable assertion, int repetitionCount) {
        this.description = description;
        this.assertion = assertion;
        this.repetitionCount = repetitionCount;
    }

    public static TestExecutor testExecutorOf(String description, ThrowingRunnable assertion, int repetitionCount)
    {
        return new TestExecutor(description, assertion, repetitionCount);
    }

    public void testExecutionTime(ConditionFactory factory) {
        for (int i = 0; i < repetitionCount; i++) {
            long beforeTime = System.nanoTime();
            long afterTime;
            try {
                factory.untilAsserted(assertion);
            } catch (Exception ex) {
                System.out.println(description + ", exception happened: " + ex.getMessage());
            } catch (Throwable throwable) {
                System.out.println(description + ", error happened: " + throwable.getMessage());
            } finally {
                afterTime = System.nanoTime();
            }
            long timeSpent = TimeUnit.MILLISECONDS.convert(afterTime - beforeTime, TimeUnit.NANOSECONDS);
            System.out.println(description + ", time spent (ms) = " + timeSpent);
        }
    }
}
