import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ThrowingRunnable;

import static util.ConditionFactoryProvider.*;
import static util.TestExecutor.testExecutorOf;

public class Main {
    public static void main(String[] args) {
        ThrowingRunnable assertion = () -> {
            throw new Error("Assertion error happened");
        };
        testAwaitilityFactories(assertion);
    }

    private static void testAwaitilityFactories(ThrowingRunnable assertion)
    {
        long pollDelaySeconds = 3;
        long timeoutSeconds = 4;
        int repetitionCount = 3;
        ConditionFactory pollDelayFactory = getPollDelay(pollDelaySeconds);
        testExecutorOf("atLeastOnly", assertion, repetitionCount).testExecutionTime(getAtLeast(timeoutSeconds));
        testExecutorOf("atMostOnly", assertion, repetitionCount).testExecutionTime(getAtMost(timeoutSeconds));
        testExecutorOf("pollDelayOnly", assertion, repetitionCount).testExecutionTime(getPollDelay(pollDelaySeconds));
        testExecutorOf("pollDelayWithAtMost", assertion, repetitionCount)
                .testExecutionTime(getPollDelayWithAtMost(pollDelayFactory, timeoutSeconds));
        testExecutorOf("pollDelayWithAtLeast", assertion, repetitionCount)
                .testExecutionTime(getPollDelayWithAtLeast(pollDelayFactory, timeoutSeconds));
    }
}
