package util;

import org.awaitility.core.ConditionFactory;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class ConditionFactoryProvider {
    public static ConditionFactory getPollDelay(long pollDelaySeconds) {
        return await().pollDelay(pollDelaySeconds, TimeUnit.SECONDS);
    }

    public static ConditionFactory getPollDelayWithAtMost(ConditionFactory pollDelayFactory, long timeoutSeconds) {
        return getAtMost(pollDelayFactory, timeoutSeconds, TimeUnit.SECONDS);
    }

    public static ConditionFactory getAtMost(ConditionFactory factory, long timeoutSeconds, TimeUnit timeUnit) {
        return factory.atMost(timeoutSeconds, TimeUnit.SECONDS);
    }

    public static ConditionFactory getAtMost(long timeoutSeconds) {
        return await().atMost(timeoutSeconds, TimeUnit.SECONDS);
    }

    public static ConditionFactory getPollDelayWithAtLeast(ConditionFactory pollDelayFactory, long timeoutSeconds) {
        return getAtLeast(pollDelayFactory, timeoutSeconds);
    }

    public static ConditionFactory getAtLeast(ConditionFactory factory, long timeout) {
        return factory.atLeast(timeout, TimeUnit.SECONDS);
    }

    public static ConditionFactory getAtLeast(long timeoutSeconds) {
        return await().atLeast(timeoutSeconds, TimeUnit.SECONDS);
    }
}
